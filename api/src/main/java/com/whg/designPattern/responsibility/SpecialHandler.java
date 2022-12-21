package com.whg.designPattern.responsibility;

public class SpecialHandler extends Handler{
    private final int number;
    public SpecialHandler(String name, int number) {
        super(name);
        this.number = number;
    }
    @Override
    public boolean resolve(Mission mission) {
        return mission.getNumber() == number;
    }
}
