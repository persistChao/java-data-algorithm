package com.answer.jvm.oom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author answer
 * @version 1.0.0
 * @date 2022/1/2 12:50 上午
 */
public class HeapOOM {
    static  class Order {
        private String orderNo;
        private String orderName;
        private Integer money;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public Integer getMoney() {
            return money;
        }

        public void setMoney(Integer money) {
            this.money = money;
        }
    }

    /**
     * set jvm -Xmx20m -Xms20m
     *
     * 通过设置一下参数 可以在发生内存溢出的时候 将但是的堆信息快照到文件中
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=/Users/suchao/dev/heapdump.hrpof
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        List<Order> list = new ArrayList<>();
        for (;;){
            Order order = new Order();
            order.setOrderName("111");
            order.setMoney(2000);
            order.setOrderNo("3333");

            //如果不妨到list中就是没有引用 order对象没有任何引用，gc会回收也就是 GCroot 不可达
            list.add(order);

//            Thread.sleep(20);

            System.out.println(list.size());
        }
    }
}
