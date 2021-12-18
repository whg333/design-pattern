package com.whg.game;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class GameUser8 {

    private IntObj hp;
    private int exp;

    private final long id;
    private final ExecutorService[] userExecutor;

    private final CountDownLatch latch;
    private final boolean bindUserQueue;

    public GameUser8(long id, int hp, ExecutorService[] userExecutor,
                     CountDownLatch latch, boolean bindUserQueue) {
        this.hp = new IntObj(hp);

        this.id = id;
        this.userExecutor = userExecutor;

        this.latch = latch;
        this.bindUserQueue = bindUserQueue;
    }

    public void exec(Runnable runnable){
        int idx = (int)id % userExecutor.length;
        userExecutor[idx].execute(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // System.out.println(latch);
                latch.countDown();
            }
        });
    }

    public void mockSecondTime(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void attack(GameUser8 other, int hp){
        if(other == null){
            return;
        }

        exp++;

        if(needMockTime()){
            mockTime();
        }

        if(bindUserQueue){
            other.exec(() -> {
                other.decrHp(hp);
            });
        }else{
            other.decrHp(hp);
        }

    }

    public void decrHp(int val){
        if(val <= 0){
            return;
        }

        int hpVal = hp.getValue();
        if(needMockTime()){
            mockTime();
        }
        hpVal = hpVal - val;
        hp.setValue(hpVal);
    }

    public void incrHp(int val){
        if(val <= 0){
            return;
        }

        int hpVal = hp.getValue();
        if(needMockTime()){
            mockTime();
        }
        hpVal = hpVal + val;
        hp.setValue(hpVal);
    }

    private boolean needMockTime(){
        // return true;
        return false;
    }

    private void mockTime(){
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getHp() {
        return hp.getValue();
    }

    public int getExp() {
        return exp;
    }

    private static class IntObj {

        int value;

        IntObj(int value){
            this.value = value;
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }
    }

}
