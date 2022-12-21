package com.whg.designPattern.proxy;

public class PrinterProxy implements Printable{

    private String name;
    private Printable real;

    public PrinterProxy(String name){
        setName(name);
    }

    @Override
    public void setName(String name) {
        this.name = name;
        if(real != null){
            real.setName(name);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print(String str) {
        if(real == null){
            real = new Printer(name);
        }
        real.print(str);
    }

    @Override
    public String toString() {
        return "PrinterProxy{" +
                "name='" + name + '\'' +
                ", real=" + real +
                '}';
    }
}
