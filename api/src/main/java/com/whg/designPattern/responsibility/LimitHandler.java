package com.whg.designPattern.responsibility;

public class LimitHandler extends Handler{
    private final int limit;
    public LimitHandler(String name, int limit) {
        super(name);
        this.limit = limit;
    }
    @Override
    public boolean resolve(Mission mission) {
        return mission.getNumber() < limit;
    }
}
