package com.answer.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/8 10:21 下午
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("my-test-group-1");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        for (int i = 0; i <100 ; i++) {
            Message msg = new Message("my-test-topic-1", "TagA", ("Hello RocketMq" + i).getBytes());
            SendResult result = producer.send(msg);
            System.out.println(">>>>>>>>SendResult：" + result);
        }

        producer.shutdown();

    }
}
