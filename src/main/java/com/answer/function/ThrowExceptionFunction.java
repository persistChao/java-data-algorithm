package com.answer.function;

/**
 * @author answer
 * @version 1.0.0
 * @date 2022/5/13 下午7:51
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    /**
     * 把消息
     *
     * @param message 消息
     */
    void throwMessage(String message);

}
