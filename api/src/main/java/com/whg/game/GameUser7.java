package com.whg.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class GameUser7 {

    private int hp;
    private int exp;

    private final long id;
    private final ExecutorService[] userExecutor;

    public GameUser7(long id, int hp, ExecutorService[] userExecutor) {
        this.hp = hp;

        this.id = id;
        this.userExecutor = userExecutor;
    }

    public void exec(Runnable runnable){
        int idx = (int)id % userExecutor.length;
        userExecutor[idx].execute(runnable);
    }

    public void mockSecondTime(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void attack(GameUser7 other, int hp){
        if(other == null){
            return;
        }

        exp++;

        if(needMockTime()){
            mockTime();
        }

        other.decrHp(hp);
    }

    public void decrHp(int val){
        if(val <= 0){
            return;
        }

        if(needMockTime()){
            mockTime();
        }

        hp -= val;
    }

    private boolean needMockTime(){
        return true;
        // return false;
    }

    private void mockTime(){
        try {
            TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getHp() {
        return hp;
    }

    public int getExp() {
        return exp;
    }

}
