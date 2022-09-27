package com.answer.designpatterns.chain;

/**
 * 请假系统 客户端
 * 在类中创建并使用责任链（向责任链传递请求）
 *
 * @author suchao
 * @date 2022/9/27 11:09
 **/
public class OASystem {
    public static void main(String[] args) {
        //创建具体处理者
        Handler pm = new PmHandler();
        Handler director = new DirectorHandler();
        Handler minister = new MinisterHandler();
        //构建责任链
        pm.setNext(director);
        director.setNext(minister);
        //使用责任链
        pm.handleRequest("answer", 8);
    }
}
