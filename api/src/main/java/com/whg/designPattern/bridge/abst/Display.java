package com.whg.designPattern.bridge.abst;

import com.whg.designPattern.bridge.impl.Displayer;
import com.whg.designPattern.template.AbstractDisplay;

public class Display extends AbstractDisplay {

    private final Displayer impl;

    public Display(Displayer impl){
        this.impl = impl;
    }

    @Override
    public void open(){
        impl.doOpen();
    }

    @Override
    public void print(){
        impl.doPrint();
    }

    @Override
    public void close(){
        impl.doClose();
    }

}
