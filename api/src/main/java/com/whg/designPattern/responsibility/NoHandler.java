package com.whg.designPattern.responsibility;

public class NoHandler extends Handler{
    public NoHandler(String name) {
        super(name);
    }
    @Override
    public boolean resolve(Mission mission) {
        return false;
    }
}
