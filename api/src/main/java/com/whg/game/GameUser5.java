package com.whg.game;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class GameUser5 {

    private int hp;
    private int exp;

    private final ReentrantLock lock = new ReentrantLock();

    public GameUser5(int hp) {
        this.hp = hp;
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }

    public void attack(GameUser5 other, int hp){
        if(other == null){
            return;
        }

        // lock();

        exp++;

        if(needMockTime()){
            mockTime();
        }

        other.decrHp(hp);

        // unlock();
    }

    public void decrHp(int val){
        if(val <= 0){
            return;
        }

        // lock();

        if(needMockTime()){
            mockTime();
        }

        hp -= val;

        // unlock();
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
