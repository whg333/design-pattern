package com.whg.designPattern.bridge;

import com.whg.designPattern.bridge.abst.ComplexCountDisplay;
import com.whg.designPattern.bridge.abst.CountDisplay;
import com.whg.designPattern.bridge.abst.Display;
import com.whg.designPattern.bridge.abst.RandomCountDisplay;
import com.whg.designPattern.bridge.impl.ComplexDisplayer;
import com.whg.designPattern.bridge.impl.FileDisplayer;
import com.whg.designPattern.bridge.impl.StringDisplayer;

public class Main {
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayer("Hello, China."));
        Display d2 = new CountDisplay(new StringDisplayer("Hello, World."));
        CountDisplay d3 = new CountDisplay(new StringDisplayer("Hello, Universe."));
        RandomCountDisplay d4 = new RandomCountDisplay(new StringDisplayer("Hello, Random."));

        String pwd = Main.class.getResource("").getPath();
        // String pwd = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(pwd);
        pwd = pwd.replace("target/classes", "src/main/java");
        System.out.println(pwd);
        Display d5 = new Display(new FileDisplayer(pwd+"impl/FileDisplayer.java"));
        CountDisplay d6 = new CountDisplay(new FileDisplayer(pwd+"impl/FileDisplayer.java"));

        CountDisplay d7 = new ComplexCountDisplay(new ComplexDisplayer("<", "*", ">"));
        CountDisplay d8 = new ComplexCountDisplay(new ComplexDisplayer("|", "##", "-"));

        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);
        d4.display();
        d4.randomDisplay(5);

        d5.display();
        d6.multiDisplay(2);

        // d7.display();
        d7.multiDisplay(4);
        // d8.display();
        d8.multiDisplay(6);
    }
}
