package com.answer.mq;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/9 4:20 下午
 */
public class TransactionListenerImpl implements TransactionListener {

    //存储对应事务的id和状态 key：事务Id value：当前事务执行的状态
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 执行本地事务
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //事务id
        String transactionId = msg.getTransactionId();

        //0 执行成功 1 本地事务执行成功 2 本地事务执行失败

        localTrans.put(transactionId, 0);

        //业务执行，处理本地事务，service
        System.out.println("hello Transaction Demo");

        try {
            System.out.println("正在执行本地事务-----");
            Thread.sleep(1000*60+10000);
            System.out.println("正在执行本地事务-----成功！");
            localTrans.put(transactionId, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            localTrans.put(transactionId, 2);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }


    /**
     * 消息回查 事务回查
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        //1 获取对应事务id状态
        String transId = msg.getTransactionId();

        //获取对应事务id的执行状态
        Integer status = localTrans.get(transId);

        System.out.println("消息回查---TransactionId:"+ transId + " 状态：" + status);
        switch (status){
            case 0:
                return LocalTransactionState.UNKNOW;

            case 1:

                return LocalTransactionState.COMMIT_MESSAGE;

            case 2:

                return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.UNKNOW;
    }

}
