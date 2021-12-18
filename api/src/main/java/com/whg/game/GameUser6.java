package com.whg.game;

import java.util.concurrent.TimeUnit;

public class GameUser6 {

    private int hp;
    private int exp;

    public GameUser6(int hp) {
        this.hp = hp;
    }

    public void attack(GameUser6 other, int hp){
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
