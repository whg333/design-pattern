package com.whg.game;

public class GameUser3 {

    private int hp;
    private int exp;

    public GameUser3(int hp) {
        this.hp = hp;
    }

    public synchronized void attack(GameUser3 other, int hp){
        if(other == null){
            return;
        }

        exp++;
        other.decrHp(hp);
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

    public synchronized int getExp() {
        return exp;
    }

}
