package com.answer.function;

import java.util.function.Consumer;

/**
 * @author answer
 * @version 1.0.0
 * @date 2022/5/13 下午8:04
 */
@FunctionalInterface
public interface PresentOrElseHandle<T extends Object> {

    /**
     * 目前,否则handle
     *
     * @param action      行动
     * @param emptyAction 空的行动
     */
    void presentOrElseHandle(Consumer<? super  T> action , Runnable emptyAction);
}
