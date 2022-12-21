package com.whg.designPattern.template;

public abstract class AbstractDisplay {

    // 模板方法模式
    public final void display(){
        open();
        print();
        close();
    }

    public abstract void open();
    public abstract void print();
    public abstract void close();

}
