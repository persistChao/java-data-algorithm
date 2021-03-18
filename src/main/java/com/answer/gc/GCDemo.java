package com.answer.gc;

import java.util.Random;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew+Tenured) 串行
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC 年轻代串行 老年代并行
 *      gc提示 ParNew 年轻代 Tenured 老年代
 *       [ParNew: 2545K->2545K(3072K), 0.0000089 secs][Tenured: 6741K->2956K(6848K), 0.0018427 secs] 9286K->2956K(9920K), [Metaspace: 3092K->3092K(1056768K)], 0.0018729 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParallelGC 年轻代并行收集器   老年代并行收集器（PSYong + ParOldGen）
 *      gc示例
 *      [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 3879K->3828K(7168K)] 3879K->3828K(8704K), [Metaspace: 3101K->3101K(1056768K)]
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParallelOldGC 老年代用parallel old 年轻代用 parallel scavenge
 *  示例
 *      [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 3886K->3867K(7168K)] 3886K->3867K(8704K), [Metaspace
 * 不加就算用 UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 * CMS 并发标记清除
 * 配置了 -XX:+UseConcMarkSweepGC 打印了 -XX:+UseConcMarkSweepGC + XX:+UseParNewGC young去用 Par new
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (par new gen + concurrent mark sweep)
 *  示例
 *      [GC (CMS Initial Mark) 初始标记 [1 CMS-initial-mark: 3804K(6848K)] 6254K(9920K), 0.0002917 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-mark-start] 并发标记开始
 * [GC (Allocation Failure) [ParNew: 2510K->165K(3072K), 0.0013461 secs] 6315K->6257K(9920K), 0.0013613 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-mark: 0.001/0.002 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-preclean-start] 并发标记预清理开始
 * [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (CMS Final Remark) 重新标记[YG occupancy: 1317 K (3072 K)][Rescan (parallel) , 0.0001753 secs][weak refs processing, 0.0000220 secs][class unloading, 0.0001809 secs][scrub symbol table, 0.0002530 secs][scrub string table, 0.0001442 secs][1 CMS-remark: 6091K(6848K)] 7408K(9920K), 0.0008129 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-sweep-start]并发清除开始
 *
 *
 * G1 垃圾收集器
 * Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 * 回收日志 只有heap 和metaspace 没有了 young
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 9:28 下午
 */
public class GCDemo {

    public static void main(String[] args) {
        System.out.println("**********hello GC");
        try {
            String str = "GCDemo";
            while (true) {
                str += str + new Random().nextInt(11888833) + new Random().nextInt(99999);
                str.intern();
            }
        }catch (Throwable e){
            e.printStackTrace();

        }
    }
}
