package com.whg.game;

import java.util.concurrent.locks.ReentrantLock;

public class GameUser4 {

    private int hp;
    private int exp;

    private final ReentrantLock lock = new ReentrantLock();

    public GameUser4(int hp) {
        this.hp = hp;
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }

    public void attack(GameUser4 other, int hp){
        if(other == null){
            return;
        }

        lock();

        exp++;

        other.decrHp(hp);

        unlock();
    }

    public void decrHp(int val){
        if(val <= 0){
            return;
        }

        lock();

        hp -= val;

        unlock();
    }

    public int getHp() {
        return hp;
    }

    public int getExp() {
        return exp;
    }

}
