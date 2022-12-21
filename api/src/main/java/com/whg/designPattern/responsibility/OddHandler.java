package com.whg.designPattern.responsibility;

public class OddHandler extends Handler{
    public OddHandler(String name) {
        super(name);
    }
    @Override
    public boolean resolve(Mission mission) {
        return mission.getNumber() % 2 == 1;
    }
}
