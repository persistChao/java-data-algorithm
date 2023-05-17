package com.answer.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * cas 是什么 比较并交换
 * 如果线程的期望值和主内存的真实值一样则进行赋值
 * 底层的原理 unSafe
 * this 当前对象 valueOffset内存偏移量--内存地址
 * public final int getAndIncrement() {
 *         return unsafe.getAndAddInt(this, valueOffset, 1);
 *     }
 *
 *     unSafe.class
 *     UnSafe unsafe = UnSafe.getUnsafe();
 *     是cas的核心类 由于Java是无法访问底层系统的 需要通过本地（native）方法来访问，UnSafe相当于一个后门，基于该类可以直接操作待定内存的数据，
 *     UnSafe类存在预sum.misc包中 其内部方法操作可以像C语言的指针一样直接操作内存，因为Java中CAS操作的执行依赖于UnSafe类的方法
 *     注意:unsafe类中的所有方法都是native修饰的，也就是说UnSafe类中的方法都直接调用操作系统底层的资源执行相应的任务
 *
 *     变量valueOffset表示变量值 在内存中的地址，因为UnSafe就是根据内存变量的偏移地址获取数据的
 *
 *     变量value使用volatile修饰保证了多线程之间内存的可见性
 *
 *
 *     CAS他是一条cpu并发原语 他的功能是判断内存某个位置的值是否是预期值，如果是则更改新的值，这过程是原子的
 *
 *     cas并发原语体现在Java语言中就是sun.misc.unsafe类中的各个方法，调用UnSafe类中的cas方法，jvm会帮我们实现出cas汇编指令，这是一种完全依赖与硬件的功能
 *     通过他实现了原子操作，注意 由于cas是一种系统原语，原语属于操作系统用语范畴，是有若干条指令组成的，
 *     用于完成某个功能的一个过程，并且原语的执行必须是连续的，在执行过程中不允许被中断，也就是说cas是一条cpu的原子指令，不会造成所谓的数据不一致问题
 *
 *     public final int getAndAddInt(Object var1, long var2, int var4) {
 *         int var5;
 *         do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *
 *         return var5;
 *     }
 *
 *     为什么用cas 不用synchronized :
 *          因为synchronize的 加锁同一时间段只能有一个线程访问，保证了一致性 但是并发性下降 cas 没有加锁
 *     cas缺点：
 *          1循环时间长（cpu）开销大 如果cas失败 会一直尝试，如果长时间一直不成功，可能会给cpu带来很大的开销；
 *
 *          2 只能保证一个共享变量的原子性(不能对一段代码操作)
 *          当对一个共享变量执行操作时，我们可以使用循环cas来保证原子操作，但是，对多个共享变量操作时，循环cas就无法保证操作的原子性，这个时候就可以用锁来保证原子性
 *
 *          3 ABA问题
 *              原子引用 AtomicReference + 新增一种机制，就是修改版本号
 *
 *
 *
 * @author answer swap
 * @version 1.0
 * @date 2021/2/25 6:21 下午
 */
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(5);



        System.out.println(a.compareAndSet(5, 2019) + "\t current data:" + a.get());


        System.out.println(a.compareAndSet(5, 1024) + "\t current data:" + a.get());
        System.out.println(a.compareAndSet(2019, 1024) + "\t current data:" + a.get());

    }
}
