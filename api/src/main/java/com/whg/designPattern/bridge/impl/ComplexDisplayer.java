package com.whg.designPattern.bridge.impl;

public class ComplexDisplayer implements Displayer{

    private String prefix;
    private String infix;
    private String suffix;

    public ComplexDisplayer(String prefix, String infix, String suffix){
        this.prefix = prefix;
        this.infix = infix;
        this.suffix = suffix;
    }

    @Override
    public void doOpen() {
        System.out.print(prefix);
    }

    @Override
    public void doPrint() {
        System.out.print(infix);
    }

    @Override
    public void doClose() {
        System.out.println(suffix);
    }
}
