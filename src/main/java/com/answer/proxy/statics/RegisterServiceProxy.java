package com.answer.proxy.statics;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/11 10:57 上午
 */
public class RegisterServiceProxy implements IRegisterService{

    IRegisterService iRegisterService;

    public RegisterServiceProxy(IRegisterService iRegisterService) {
        this.iRegisterService = iRegisterService;
    }

    @Override
    public void register(String name, String pwd) {
        System.out.println("[proxy]一些前置处理");
        System.out.println(String.format("[Proxy]打印注册信息：姓名：%s,密码：%s", name, pwd));
        iRegisterService.register(name, pwd);
        System.out.println("[proxy]一些后置处理");
    }
}
