package com.whg.designPattern.template;

public class CharDisplay extends AbstractDisplay{

    private final char ch;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        for(int i=0;i<3;i++){
            System.out.print(ch);
        }
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
