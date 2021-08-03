package com.answer.nio.netty.protocoltcp;

import lombok.Data;

/**
 * 协议包定义
 * @author answer
 * @version 1.0.0
 * @date 2021/8/3 3:29 下午
 */
@Data
public class MessageProtocol {
    /**
     * 消息长度
     */
    private int len;

    private byte[] content;
}
