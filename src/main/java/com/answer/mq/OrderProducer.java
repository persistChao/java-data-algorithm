package com.answer.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * 顺序消息
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/4/9 2:19 上午
 */
public class OrderProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("my-test-group-1");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        //发送消息 1发送的消息信息 2 选择指定的消息队列对象（会将所有消息队列传进来） 3 指定对应的队列下标


        for (int i = 0; i < 10; i++) {
            String message = ("Order Message" + i);
            Message msg = new Message("my-test-topic-1", "TagA", message.getBytes());
            SendResult result = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    //获取队列的下标
                    Integer index = (Integer) arg;

                    return mqs.get(index);
                }
            }, 1);
            System.out.println(">>>>>>>>SendResult：" + result);
        }

        producer.shutdown();
    }
}
