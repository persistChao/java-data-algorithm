package com.answer.proxy.statics;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/11 10:55 上午
 */
public class RegisterServiceImpl implements IRegisterService{
    @Override
    public void register(String name, String pwd) {
        System.out.println(String.format("向数据库插入数据 name:%s , pwd %s", name, pwd));

    }
}
