package com.answer.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事务消息
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/4/9 2:19 上午
 */
public class TransactionProducer {


    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-transactional-msg-check-thread");
                        return thread;
                    }
                });
        //1 创建DefaultMQProducer
        TransactionMQProducer producer = new TransactionMQProducer("my-test-group-1");

        //2 设置namesrv地址
        producer.setNamesrvAddr("localhost:9876");

        // 指定消息监听 用于执行本地事务，和消息回查
        TransactionListener transactionListener = new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);
        //因为事务消息是异步的，事务 回查 是多线程的 所以要设置线程池
        producer.setExecutorService(executor);

        //3 启动producer
        producer.start();

        String message = "hello Transactional message";

        //发送消息 1发送的消息信息 2 选择指定的消息队列对象（会将所有消息队列传进来） 3 指定对应的队列下标
        Message msg = new Message("Topic_Transactional", "TagT","Keys_T", message.getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult sendResult = producer.sendMessageInTransaction(msg, "hello transactional");
        System.out.println("发送事务消息result=" + sendResult);
        producer.shutdown();
    }
}
