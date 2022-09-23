package com.answer.function.function;

/**
 * 函数式接口
 *  1 在接口中只能存在一个抽象方法
 *  2 允许定义默认方法 默认方法在接口中可以直接写方法体 写实现
 *  3 允许定义静态方法
 *  4 允许定义java.lang.Object的public方法
 * @author suchao
 * @date 2022/9/23 15:33
 **/
@FunctionalInterface
public interface HelloFunctionInterface {
    /**
     * 说“你好”
     *
     * @param name 名字
     */
    void sayHello(String name);


//    void sayHello(); 不能存在两个抽象方法

    /**
     * 说“你好”
     */
    default void sayHello() {
        System.out.println("This is a default method ");
    }

    /**
     * 打印消息
     *
     * @param msg 味精
     */
    static void printMsg(String msg){
        System.out.println("This is a static method in FunctionalInterface");
    }

    /**
     * 函数式接口里是可以包含Object里的public方法，这些方法对于函数式接口来说，不被当成是抽象方法（虽然它们是抽象方法）；
     * 因为任何一个函数式接口的实现，默认都继承了Object类，包含了来自java.lang.Object里对这些抽象方法的实现；
     **/
    @Override
    boolean equals(Object object);
}
