package com.whg.designPattern.responsibility;

public class Mission {
    private final int number;
    public Mission(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    @Override
    public String toString() {
        return "Mission{" +
                "number=" + number +
                '}';
    }
}
