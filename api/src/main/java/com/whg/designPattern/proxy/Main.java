package com.whg.designPattern.proxy;

public class Main {
    public static void main(String[] args) {
        Printable p = new PrinterProxy("Alice");
        System.out.println(p);
        p.setName("Bob");
        System.out.println(p);
        p.print("Hello, world.");
        System.out.println(p);
    }
}
