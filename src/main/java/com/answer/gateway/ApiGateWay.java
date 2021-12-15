package com.answer.gateway;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/17 9:18 下午
 */
public class ApiGateWay {
    private static OkHttpClient okHttpClient;

    private static final int CODE_200 = 200;

    private static final int RE_TRY_MAX = 3;

    private static final BlockingQueue<TrafficRepository> REP_QUEUE = new ArrayBlockingQueue<>(10000);

    static ThreadPoolExecutor executor = null;

    static {
        ConnectionPool connectionPool = new ConnectionPool(100, 3, TimeUnit.MINUTES);
        okHttpClient = new OkHttpClient.Builder().connectionPool(connectionPool)
                .retryOnConnectionFailure(true)
                .connectTimeout(6, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS).build();

        if (executor == null) {
            executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
                    100, 1,
                    TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

            executor.submit(new SendTrafficRepository(executor));
        }
    }

    public static Response doForward(String url) {
        Response response = null;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    /**
     * @param uri                   请求uri，简化起见假设所有请求都是get请求，没有request body。例如http://api.huami.com/users/-/profile
     * @param targetServiceHost     下游微服务host，例如 http://a.huami.com
     * @param trafficRepositoryHost 流量存储服务host，例如 http://traffic.huami.com
     * @return 应该返回客户端的http status code，例如200，404，或者500等
     */
    //思路 两个转发互不影响 流量存储的转发不需要关系实时性，只要能保存在存储服务中即可
    public int forward(String uri, String targetServiceHost, String trafficRepositoryHost) {
        // 返回http status code
        String request = targetServiceHost + uri;
        Response response = doForward(request);
        //考虑两个线程做两个转发的工作，但是第一个转发的response 需要在第二个线程中异步获取
        //
        try {
            REP_QUEUE.put(new TrafficRepository(request, response, trafficRepositoryHost));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response.code();
    }

    static class SendTrafficRepository implements Runnable {
        final ThreadPoolExecutor executor;

        public SendTrafficRepository(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            while (!this.executor.isShutdown()) {
                System.out.println("监听延迟队列..." + REP_QUEUE.size());
                try {
                    TrafficRepository t = REP_QUEUE.take();
                    String url = t.getTrafficRepositoryHost() + "?request=" + t.getRequest() + "&response=" + JSON.toJSONString(t.getResponse());
                    Response re = doForward(url);
                    if (re.code() != CODE_200) {
                        int reTryCount = RE_TRY_MAX;
                        while (reTryCount > 0) {
                            re = doForward(url);
                            if (re.code() != CODE_200) {
                                reTryCount--;
                            } else {
                                break;
                            }
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    @Data
    @AllArgsConstructor
    static class TrafficRepository {
        private String request;

        private Response response;

        private String trafficRepositoryHost;

    }

    public static void main(String[] args) {
        ApiGateWay apiGateWay = new ApiGateWay();
        apiGateWay.forward("http://www.baidu.com", "a.com", "transfer.com");
    }
}
