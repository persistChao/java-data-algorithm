package com.answer.designpatterns.chain;

/**
 * 请假系统，组长审批 经理审批 部长审批。。。
 *
 * @author suchao
 * @date 2022/9/27 11:04
 **/
public abstract class Handler {
    protected Handler next;

    /**
     * 处理请求
     * 抽象的，有三个子类具体实现
     *
     * @param name 名字
     * @param days 天
     */
    public abstract void handleRequest(String name , int days);

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
