package com.answer.function;

/**
 * @author answer
 * @version 1.0.0
 * @date 2022/5/13 下午7:53
 */
@FunctionalInterface
public interface BranchHandle {

    /**
     * 真或假处理
     *
     * @param trueHandle  正确的处理
     * @param falseHandle 错误的处理
     */
    void trueOrFalseHandle(Runnable trueHandle,Runnable falseHandle);
}
