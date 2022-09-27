package com.answer.function;

import cn.hutool.core.date.DateUtil;
import com.answer.User;
import org.junit.Test;

import java.util.Date;

/**
 * @author answer
 * @version 1.0.0
 * @date 2022/5/13 下午7:55
 */
public class FunctionTest {

    @Test
    public void testTrueOrFalse() {
        boolean isNow = false;
        Date date = new Date();
        if (DateUtil.month(date)  == 8){
            isNow = true;
        }

        User user = new User();
        FunctionUtil.isTrueOrFalse(isNow).trueOrFalseHandle(() -> {
            System.out.println("现在是第三季度");
            user.setName("八月");
            user.setAge(17);
        }, () -> {
            System.out.println("现在不是九月");
            user.setName("九月");
            user.setAge(18);
        });
        System.out.println(user);
    }

    @Test
    public void isBlankOrNotBlank() {
        FunctionUtil.isBlankOrNotBlank("Hello ").presentOrElseHandle(System.out::println, () -> System.out.println("字符串为空"));
    }
}
