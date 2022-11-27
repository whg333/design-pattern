package com.whg.designPattern.bridge.abst;

import com.whg.designPattern.bridge.impl.Displayer;

public class CountDisplay extends Display{
    public CountDisplay(Displayer impl){
        super(impl);
    }
    public void multiDisplay(int count){
        open();
        for(int i=0;i<count;i++){
            print();
        }
        close();
    }
}
