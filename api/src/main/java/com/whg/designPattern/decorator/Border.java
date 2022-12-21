package com.whg.designPattern.decorator;

public abstract class Border extends Display{
    protected final Display display;
    public Border(Display display) {
        this.display = display;
    }
    public static String makeLine(char ch, int count){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<count;i++){
            sb.append(ch);
        }
        return sb.toString();
    }
}
