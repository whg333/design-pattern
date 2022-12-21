package com.whg.designPattern.observer;

import java.util.Random;

public class RandomNumberGenerator extends AbstractSubject{

    private final Random random = new Random();
    private int number;

    @Override
    public Object getStatus() {
        return number;
    }

    @Override
    public void publish(){
        for(int i=0;i<20;i++){
            number = random.nextInt(50);
            notifyObservers();
        }
    }
}
