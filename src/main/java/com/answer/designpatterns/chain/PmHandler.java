package com.answer.designpatterns.chain;

/**
 * @author suchao
 * @date 2022/9/27 11:06
 **/
public class PmHandler extends Handler {
    @Override
    public void handleRequest(String name, int days) {
        System.out.println("责任链第【1】步 PM handler");
        if (days <= 3) {
            System.out.println(name + "，组长已经同意您的请假审批!");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(name, days);
            } else {
                System.out.println("请假天数太多，申请被驳回！");
            }
        }
    }
}
