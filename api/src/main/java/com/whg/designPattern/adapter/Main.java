package com.whg.designPattern.adapter;

public class Main {
    public static void main(String[] args) {
        Print p1 = new PrintBanner("Hello");
        p1.printWeak();
        p1.printStrong();
        Print p2 = new PrintBannerProxy("World");
        p2.printWeak();
        p2.printStrong();
    }
}
