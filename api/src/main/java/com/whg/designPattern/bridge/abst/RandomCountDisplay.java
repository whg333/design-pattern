package com.whg.designPattern.bridge.abst;

import com.whg.designPattern.bridge.impl.Displayer;

import java.util.Random;

public class RandomCountDisplay extends CountDisplay{
    private Random random = new Random();
    public RandomCountDisplay(Displayer impl) {
        super(impl);
    }
    public void randomDisplay(int count){
        open();
        for(int i=0;i<Math.max(1, random.nextInt(count+1));i++){
            print();
        }
        close();
    }
}
