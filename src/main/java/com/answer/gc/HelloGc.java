package com.answer.gc;

/**
 *
 * -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:MetaspaceSize=31457280 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC
 * hello Gc
 * [GC (Allocation Failure) [PSYoungGen: 1615K->496K(2560K)] 1615K->591K(9728K), 0.0011980 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [PSYoungGen: 496K->496K(2560K)] 591K->599K(9728K), 0.0011440 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 * [Full GC (Allocation Failure) [PSYoungGen: 496K->0K(2560K)] [ParOldGen: 103K->453K(7168K)] 599K->453K(9728K), [Metaspace: 2917K->2917K(1056768K)], 0.0041982 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
 * [GC (Allocation Failure) [PSYoungGen: 0K->0K(2560K)] 453K->453K(9728K), 0.0004735 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2560K)] [ParOldGen: 453K->436K(7168K)] 453K->436K(9728K), [Metaspace: 2917K->2917K(1056768K)], 0.0037449 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 * Heap
 *  PSYoungGen      total 2560K, used 81K [0x00000007bfd00000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 2048K, 3% used [0x00000007bfd00000,0x00000007bfd144d8,0x00000007bff00000)
 *   from space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
 *   to   space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
 *  ParOldGen       total 7168K, used 436K [0x00000007bf600000, 0x00000007bfd00000, 0x00000007bfd00000)
 *   object space 7168K, 6% used [0x00000007bf600000,0x00000007bf66d100,0x00000007bfd00000)
 *  Metaspace       used 2963K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 322K, capacity 388K, committed 512K, reserved 1048576K
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at com.answer.gc.HelloGc.main(HelloGc.java:12)
 *
 * 进程已结束,退出代码1
 * @author answer
 * @version 1.0.0
 * @date 2021/3/4 4:12 下午
 */
public class HelloGc {
    // -Xms10m -Xmx10m -XX:MetaspaceSize=30m -XX:+PrintGCDetails
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello Gc");
        byte[] bytes = new byte[1024*1024*50];

    }
}
