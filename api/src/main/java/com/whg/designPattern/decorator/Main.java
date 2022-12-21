package com.whg.designPattern.decorator;

public class Main {
    public static void main(String[] args) {
        test3();
    }

    private static void test1(){
        Display d1 = new StringDisplay("Hello, world.");
        Display d2 = new SideBorder(d1, '#');
        Display d3 = new FullBorder(d2);
        d1.show();
        d2.show();
        d3.show();
        Display d4 =
                new SideBorder(
                        new FullBorder(
                                new SideBorder(
                                        new FullBorder(
                                                new StringDisplay("test border")
                                        ), '*')
                        ), '/');
        d4.show();
    }

    private static void test2(){
        Display d1 = new StringDisplay("Hello, world.");
        Display d2 = new UpDownBorder(d1, '-');
        Display d3 = new SideBorder(d2, '*');
        d1.show();
        d2.show();
        d3.show();
        Display d4 =
                new FullBorder(
                        new UpDownBorder(
                                new SideBorder(
                                        new UpDownBorder(
                                                new SideBorder(
                                                        new StringDisplay("test border"),
                                                    '*'),
                                            '='),
                                    '|'),
                            '/')
                );
        d4.show();
    }

    private static void test3(){
        MultiStringDisplay d1 = new MultiStringDisplay();
        d1.add("good morning.");
        d1.add("good afternoon!");
        d1.add("Good night. See you tomorrow.");
        d1.show();

        Display d2 = new SideBorder(d1, '#');
        d2.show();

        Display d3 = new FullBorder(d1);
        d3.show();
    }
}
