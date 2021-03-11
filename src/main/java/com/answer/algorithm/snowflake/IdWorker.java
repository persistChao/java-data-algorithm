package com.answer.algorithm.snowflake;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/9 3:54 下午
 */
public class IdWorker {
    /**
     * 机器id 2进制5位 32位减1位 31个
     */
    private long workerId;

    /**
     * 机房id 2进制5位 等于32 减掉1个 = 31个
     */
    private long dataCenterId;

    /**
     * 机器id 2进制5位 等于32 减掉1个 = 31个 这里是5位 代表位数
     */
    private static long workerIdBits = 5L;

    /**
     * 5位的机房id 这里代表位数是5位
     */
    private long dataCenterIdBits = 5L;

    /**
     * 代表一毫秒内生成的多个id的最新序列号 2进制 12位 减掉1 = 4095个
     */
    private long sequence;

    /**
     * 设置一个时间初始值    2^41 - 1   差不多可以用69年
     */
    private long twepoch = 1585644268888L;

    /**
     * 每秒内生成的随机数id 2的12次方 这里代表位数
     */
    private long sequenceBits = 12L;

    /**
     * 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
     */
    private long maxDatacenterId = -1L ^ (-1L << dataCenterIdBits);

    private long workerIdShift = sequenceBits;

    private long dataCenterIdShift = sequence + workerIdBits;

    private long timeStampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;


    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 记录产生时间毫秒数，判断是否是同1毫秒
     */
    private long lastTimestamp = -1L;


    public long getWorkerId() {
        return workerId;
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public IdWorker(long workerId, long dataCenterId, long sequence) {
        // 检查机房id和机器id是否超过31 不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("worker id can't be greater or less than 0");
        }

        if (dataCenterId > maxDatacenterId || dataCenterId < 0) {
            throw new IllegalArgumentException("data center id can't be greater or less than 0");
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    /**
     * 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
     * @return
     */
    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timeStamp = timeGen();
        if (timeStamp < lastTimestamp) {
            System.out.printf("clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
        }
        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timeStamp) {
            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;// 如果sequence=30 再加1 就是31 31&31=31 &原算法
            //当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
            if (sequence == 0) {
                timeStamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timeStamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timeStamp - twepoch) << timeStampLeftShift | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timeStamp = timeGen();
        while (timeStamp <= lastTimestamp) {
            timeStamp = timeGen();
        }
        return timeStamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
//        long sequenceMask = 31;
//        long sequence = 30;
//        System.out.println( (sequence + 1) & sequenceMask);
//
//        System.out.println("(0&0) = " + (0&0));
//        System.out.println("(0&1) = " + (0&1));
//        System.out.println("(1&1) = " + (1&1));

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 1000, 1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        IdWorker idWorker = new IdWorker(1, 1, 1);
        try {
            for (int i = 0; i <1000 ; i++) {

                int finalI = i;
                executor.execute(()->{
                    System.out.println(idWorker.nextId() + "   " + (finalI +1));
                });
            }
        }finally {
            executor.shutdown();
        }


    }
}
