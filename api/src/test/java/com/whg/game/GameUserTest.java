package com.whg.game;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameUserTest {

    public static void main(String[] args) throws Exception {
        for(int i=1;i<=1000;i++){
            // test1(i);
            // test2(i);
            // test3(i);
            // test4(i);
            // test5(i);
            test6(i);
        }
    }

    private static void test1(int i) throws Exception {
        GameUser1 user = new GameUser1(100);

        Thread t1 = new Thread(() -> {
            user.decrHp(1);
        });
        Thread t2 = new Thread(() -> {
            user.decrHp(1);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int hp = user.getHp();
        if(hp != 98){
            throw new RuntimeException("第"+i+"次减血，剩余血量[hp="+hp+"]");
        }else{
            System.out.println("第"+i+"次减血，剩余血量[hp="+hp+"]");
        }
    }

    private static void test2(int i) throws Exception {
        GameUser2 user = new GameUser2(100);

        Thread t1 = new Thread(() -> {
            user.decrHp(1);
        });
        Thread t2 = new Thread(() -> {
            user.decrHp(1);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int hp = user.getHp();
        if(hp != 98){
            throw new RuntimeException("第"+i+"次减血，剩余血量[hp="+hp+"]");
        }else{
            System.out.println("第"+i+"次减血，剩余血量[hp="+hp+"]");
        }
    }

    private static void test3(int i) throws Exception {
        GameUser3 u1 = new GameUser3(50);
        GameUser3 u2 = new GameUser3(100);

        Thread t1 = new Thread(() -> {
            u1.attack(u2, 1);
        });
        Thread t2 = new Thread(() -> {
            u2.attack(u1, 1);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int hp = u2.getHp();
        if(hp != 99){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，剩余血量[hp="+hp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，剩余血量[hp="+hp+"]");
        }
    }

    private static void test4(int i) throws Exception {
        GameUser4 u1 = new GameUser4(50);
        GameUser4 u2 = new GameUser4(100);

        Thread t1 = new Thread(() -> {
            u1.attack(u2, 1);
        });
        Thread t2 = new Thread(() -> {
            u2.attack(u1, 1);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int hp = u2.getHp();
        if(hp != 99){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，剩余血量[hp="+hp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，剩余血量[hp="+hp+"]");
        }
    }

    //需要保证加锁的顺序，才能规避死锁
    private static void test5(int i) throws Exception {
        GameUser5 u1 = new GameUser5(50);
        GameUser5 u2 = new GameUser5(100);

        Thread t1 = new Thread(() -> {
            // u1.lock();
            // u2.lock();
            u1.attack(u2, 1);
            // u2.unlock();
            // u1.unlock();
        });
        Thread t2 = new Thread(() -> {
            // u1.lock();
            // u2.lock();
            u1.attack(u2, 1);
            // u2.unlock();
            // u1.unlock();
        });
        Thread t3 = new Thread(() -> {
            // u1.lock();
            // u2.lock();
            u2.attack(u1, 1);
            // u2.unlock();
            // u1.unlock();
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        int u1Exp = u1.getExp();
        if(u1Exp != 2){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，u1经验[exp="+u1Exp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，u1经验[exp="+u1Exp+"]");
        }

        int u2Hp = u2.getHp();
        if(u2Hp != 98){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，u2血量[hp="+u2Hp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，u2血量[hp="+u2Hp+"]");
        }
    }

    //使用队列来保证执行顺序
    private static void test6(int i) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        GameUser6 u1 = new GameUser6(50);
        GameUser6 u2 = new GameUser6(100);

        Thread t1 = new Thread(() -> {
            executor.execute(() -> {
                u1.attack(u2, 1);
                latch.countDown();
            });
        });
        Thread t2 = new Thread(() -> {
            executor.execute(() -> {
                u1.attack(u2, 1);
                latch.countDown();
            });
        });
        Thread t3 = new Thread(() -> {
            executor.execute(() -> {
                u2.attack(u1, 1);
                latch.countDown();
            });
        });

        t1.start();
        t2.start();
        t3.start();

        // t1.join();
        // t2.join();
        // t3.join();

        latch.await();
        executor.shutdown();

        int u1Exp = u1.getExp();
        if(u1Exp != 2){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，u1经验[exp="+u1Exp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，u1经验[exp="+u1Exp+"]");
        }

        int u2Hp = u2.getHp();
        if(u2Hp != 98){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，u2血量[hp="+u2Hp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，u2血量[hp="+u2Hp+"]");
        }
    }

}
