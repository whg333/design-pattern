package com.whg.designPattern.responsibility;

public class Main {
    public static void main(String[] args) {
        Handler a = new NoHandler("a");
        Handler b = new LimitHandler("b", 100);
        Handler c = new SpecialHandler("c", 429);
        Handler d = new LimitHandler("d", 200);
        Handler e = new OddHandler("e");
        Handler f = new LimitHandler("f", 300);

        a.setNext(b).setNext(c).setNext(d).setNext(e).setNext(f);

        for(int i=0;i<500;i+=33){
            a.handle(new Mission(i));
        }
    }
}
