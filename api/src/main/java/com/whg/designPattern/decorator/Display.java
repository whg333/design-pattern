package com.whg.designPattern.decorator;

public abstract class Display {

    public final void show(){
        for(int i=0;i<getRows();i++){
            System.out.println(getRowText(i));
        }
    }

    public abstract int getColumns();
    public abstract int getRows();
    public abstract String getRowText(int row);
}
