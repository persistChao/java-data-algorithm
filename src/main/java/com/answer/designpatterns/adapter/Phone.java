package com.answer.designpatterns.adapter;

/**
 *
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 17:08
 */
public class Phone {
    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5){
            System.out.println("电鸭5V，可以充电");
        }else if (iVoltage5V.output5V() > 5){
            System.out.println("电压大于5V 不能充电");
        }
    }
}
