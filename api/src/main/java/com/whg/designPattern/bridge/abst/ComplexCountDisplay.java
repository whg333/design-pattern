package com.whg.designPattern.bridge.abst;

import com.whg.designPattern.bridge.impl.Displayer;

public class ComplexCountDisplay extends CountDisplay{
    public ComplexCountDisplay(Displayer impl) {
        super(impl);
    }

    @Override
    public void multiDisplay(int count) {
        for(int i=0;i<count;i++){
            open();
            for(int j=0;j<i;j++){
                print();
            }
            close();
        }
    }
}
