package com.whg.designPattern.bridge.abst;

import com.whg.designPattern.bridge.impl.Displayer;

public class Display {

    private Displayer impl;

    public Display(Displayer impl){
        this.impl = impl;
    }

    public void display(){
        open();
        print();
        close();
    }

    public void open(){
        impl.doOpen();
    }
    public void print(){
        impl.doPrint();
    }
    public void close(){
        impl.doClose();
    }

}
