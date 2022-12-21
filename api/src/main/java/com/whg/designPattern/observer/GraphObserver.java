package com.whg.designPattern.observer;

public class GraphObserver implements Observer{
    @Override
    public void update(Subject subject) {
        System.out.print("GraphObserver:");
        int count = (int) subject.getStatus();
        for(int i=0;i<count;i++){
            System.out.print("*");
        }
        System.out.println();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
