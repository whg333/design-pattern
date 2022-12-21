package com.whg.designPattern.template;

import java.nio.charset.Charset;

public class StringDisplay extends AbstractDisplay{

    private final String str;

    public StringDisplay(String str) {
        this.str = str;
    }

    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        for(int i=0;i<5;i++){
            printStr();
        }
    }

    protected void printStr(){
        System.out.println("|"+str+"|");
    }

    @Override
    public void close() {
        printLine();
    }

    protected void printLine(){
        System.out.print("+");
        for(int i = 0; i<str.getBytes().length; i++){
            System.out.print("-");
        }
        System.out.println("+");
    }

}
