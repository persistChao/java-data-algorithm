package com.answer.gc.oom;

/**
 * 栈内存溢出
 *      是异常还是错误 error exception?
 *      是错误 error oom OutOfMemoryError 是错误
 *     如果线程请求的栈深度大于虚拟机锁允许的最大深度，将抛出StackOverflowError异常。
 *      如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常。
 *
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 2:11 下午
 */
public class StackOverFlowErrorDemo {

    public static void main(String[] args) {
//        stackOverFlowError();

        StackOverFlowErrorDemo demo = new StackOverFlowErrorDemo();
        demo.stackLeak();
    }

    /**
     * Exception in thread "main" java.lang.StackOverflowError
     */
    private static void stackOverFlowError() {
        stackOverFlowError();
    }

    private int stackLength = 1;

    public  void stackLeak() {
        stackLength++;
        stackLeak();

    }
}
