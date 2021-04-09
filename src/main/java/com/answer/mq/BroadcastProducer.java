package com.answer.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/9 5:50 下午
 */
public class BroadcastProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("my-broadcast-group-1");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        //发送消息 1发送的消息信息 2 选择指定的消息队列对象（会将所有消息队列传进来） 3 指定对应的队列下标

        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String message = ("Order Message" + i);
            Message msg = new Message("Topic-Broadcast", "TagA", message.getBytes());
            messages.add(msg);
        }
        SendResult sendResult = producer.send(messages);

        System.out.println("Broadcast send message result is ==  " + sendResult);

        producer.shutdown();
    }
}
