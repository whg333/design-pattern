package com.whg.game;

public class GameUser2 {

    private int hp;

    public GameUser2(int hp) {
        this.hp = hp;
    }

    public synchronized void decrHp(int val){
        if(val <= 0){
            return;
        }

        hp -= val;
    }

    public synchronized int getHp() {
        return hp;
    }

}
