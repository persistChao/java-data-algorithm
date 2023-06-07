package com.answer.designpatterns.adapter;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 17:07
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V{
    @Override
    public int output5V() {
        return 0;
    }
}
