package com.whg.designPattern.observer;

public class Main {
    public static void main(String[] args) {
        Subject subject = new RandomNumberGenerator();
        subject.addObserver(new DigitObserver());
        subject.addObserver(new GraphObserver());
        subject.publish();
    }
}
