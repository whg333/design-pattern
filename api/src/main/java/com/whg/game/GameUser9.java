package com.whg.game;

import java.util.concurrent.atomic.AtomicInteger;

public class GameUser9 {

    private final AtomicInteger hp;
    private final AtomicInteger exp;
    private final AtomicInteger lv;

    public GameUser9(int hp, int exp, int lv) {
        this.hp = new AtomicInteger(hp);
        this.exp = new AtomicInteger(exp);
        this.lv = new AtomicInteger(lv);
    }

    public /*synchronized*/ void attack(GameUser9 other, int hp){
        if(other == null){
            return;
        }

        exp.incrementAndGet();
        if(exp.get() >= 100){ //尤其这种checkAndAct的操作，容易出现并发问题
            //判断临界区，可能被OS调度替换出去，延后执行
            lv.incrementAndGet();
            update(exp, 0);
        }

        other.decrHp(hp);
    }

    public void decrHp(int val){
        if(val <= 0){
            return;
        }

        update(hp, getHp()-val);
    }

    private void update(AtomicInteger ai, int destVal){
        boolean updated;
        do{
            int srcVal = ai.get();
            updated = ai.compareAndSet(srcVal, destVal);
        }while(!updated);
    }

    public int getHp() {
        return hp.get();
    }

    public int getExp() {
        return exp.get();
    }

    public int getLv() {
        return lv.get();
    }

    @Override
    public String toString() {
        return "GameUser9{" +
                "hp=" + hp +
                ", exp=" + exp +
                ", lv=" + lv +
                '}';
    }
}
