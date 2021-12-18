package com.whg.game;

public class GameUser1 {

    private int hp;

    public GameUser1(int hp) {
        this.hp = hp;
    }

    public void decrHp(int val){
        if(val <= 0){
            return;
        }
        hp -= val;
    }

    public int getHp() {
        return hp;
    }

}
