package com.whg.designPattern.bridge.impl;

public class StringDisplayer implements Displayer {

    private String str;

    public StringDisplayer(String str){
        this.str = str;
    }

    @Override
    public void doOpen() {
        printLine();
    }

    @Override
    public void doPrint() {
        System.out.println("|"+str+"|");
    }

    @Override
    public void doClose() {
        printLine();
    }

    private void printLine() {
        System.out.print("+");
        for(int i=0;i<str.length();i++){
            System.out.print("-");
        }
        System.out.println("+");
    }
}
