package com.answer.designpatterns.chain;

/**
 * @author suchao
 * @date 2022/9/27 11:08
 **/
public class MinisterHandler extends Handler{


    @Override
    public void handleRequest(String name, int days) {
        System.out.println("责任链第【3】步 Minister handler");
        if (days <= 15) {
            System.out.println(name + "，部长已经同意您的请假审批");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(name, days);
            } else {
                System.out.println("请假天数太多，申请被驳回！");
            }
        }
    }
}
