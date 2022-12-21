package com.whg.designPattern.observer;

public class DigitObserver implements Observer{
    @Override
    public void update(Subject subject) {
        System.out.println("DigitObserver:"+subject.getStatus());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
