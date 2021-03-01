package com.answer.thread.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS
 * 原子引用 能对对象使用原子性问题
 * @author answer
 * @version 1.0
 * @date 2021/2/26 10:59 上午
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User u1 = new User("u1" , 22);
        User u2 = new User("u2", 25);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(u1);

        System.out.println(atomicReference.compareAndSet(u1,u2)+"\t " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(u1,u2)+"\t " + atomicReference.get().toString());
    }


}


@Data
@AllArgsConstructor
@NoArgsConstructor
 class User {
    private String name;
    private int age;

}
