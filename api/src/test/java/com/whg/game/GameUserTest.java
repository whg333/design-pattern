package com.whg.game;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Console;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameUserTest {

    public static void main(String[] args) throws Exception {
        for(int i=1;i<=1000;i++){
            // test1(i);
            // test2(i);
            // test3(i);
            // test4(i);
            // test5(i);
            // test6(i);
            // test7(i);
            // test8(i);
            test9(i);
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

    private static void test9(int i) throws Exception {
        GameUser9 u1 = new GameUser9(50, 99, 1);
        GameUser9 u2 = new GameUser9(100, 99, 1);

        System.out.println("执行前u1="+u1);
        System.out.println("执行前u2="+u2);

        Thread t1 = new Thread(() -> {
            u1.attack(u2, 1);
        });
        Thread t2 = new Thread(() -> {
            u1.attack(u2, 1);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("执行后u1="+u1);
        System.out.println("执行后u2="+u2);

        int u1Exp = u1.getExp();
        if(u1Exp != 1){
            throw new RuntimeException("第"+i+"次u1攻击u2失败，u1经验[exp="+u1Exp+"]");
        }else{
            System.out.println("第"+i+"次u1攻击u2成功，u1经验[exp="+u1Exp+"]");
        }

        int u1Lv = u1.getLv();
        if(u1Lv != 2){
            throw new RuntimeException("第"+i+"次u1攻击u2失败，u1等级[lv="+u1Lv+"]");
        }else{
            System.out.println("第"+i+"次u1攻击u2成功，u1等级[lv="+u1Lv+"]");
        }

        int u2Hp = u2.getHp();
        if(u2Hp != 98){
            throw new RuntimeException("第"+i+"次u1攻击u2失败，u2血量[hp="+u2Hp+"]");
        }else{
            System.out.println("第"+i+"次u1攻击u2成功，u2血量[hp="+u2Hp+"]");
        }
    }

    //使用单例线程池的队列来保证执行顺序
    private static void test6(int i) throws Exception {
        //latch的作用就是在动作都执行完毕后，打开阀门验证结果，而不用去写等待多久然后才验证结果
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

        //因为使用了线程池异步执行，所以join没用了，会立即执行完毕，立即验证就会出错，应该等待latch阀门执行完毕打开
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

    //使用单例线程池的队列来保证执行顺序——会导致阻塞的问题
    private static void test7(int i) throws Exception {
        //latch的作用就是在动作都执行完毕后，打开阀门验证结果，而不用去写等待多久然后才验证结果
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService[] userExecutor = new ExecutorService[2];
        for(int idx=0;idx<userExecutor.length;idx++){
            userExecutor[idx] = Executors.newSingleThreadExecutor();
        }

        GameUser7 u1 = new GameUser7(1, 50, userExecutor);
        GameUser7 u2 = new GameUser7(2, 100, userExecutor);

        StopWatch stopWatch = new StopWatch("test7");
        stopWatch.start();

        Thread t1 = new Thread(() -> {
            executor.execute(() -> {
                u1.exec(() -> {
                    u1.attack(u2, 1);
                    u1.mockSecondTime(3);
                    latch.countDown();
                });
            });
        });
        Thread t2 = new Thread(() -> {
            executor.execute(() -> {
                u1.exec(() -> {
                    u1.attack(u2, 1);
                    u1.mockSecondTime(2);
                    latch.countDown();
                });
            });
        });
        Thread t3 = new Thread(() -> {
            executor.execute(() -> {
                u2.exec(() -> {
                    u2.attack(u1, 1);
                    u2.mockSecondTime(3);
                    latch.countDown();
                });
            });
        });

        t1.start();
        t2.start();
        t3.start();

        latch.await();
        stopWatch.stop();
        Console.log(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));

        executor.shutdown();
        for (ExecutorService executorService : userExecutor) {
            executorService.shutdown();
        }

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

    //两人互砍，但有一人能便砍边加血
    //不绑定user使用队列会有问题，因为导致了多个线程操作同一个变量，当然并发度不高的话概率就复现不出来
    private static void test8(int i) throws Exception {
        boolean bindUserQueue = false;
        //latch的作用就是在动作都执行完毕后，打开阀门验证结果，而不用去写等待多久然后才验证结果
        CountDownLatch latch = new CountDownLatch(bindUserQueue ? 8 : 4);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService[] userExecutor = new ExecutorService[2];
        for(int idx=0;idx<userExecutor.length;idx++){
            userExecutor[idx] = Executors.newSingleThreadExecutor();
        }

        GameUser8 u1 = new GameUser8(1, 50, userExecutor, latch, bindUserQueue);
        GameUser8 u2 = new GameUser8(2, 100, userExecutor, latch, bindUserQueue);

        // StopWatch stopWatch = new StopWatch("test7");
        // stopWatch.start();

        Thread t1 = new Thread(() -> {
            executor.execute(() -> {
                u1.exec(() -> {
                    u1.attack(u2, 1);
                });
            });
        });
        Thread t2 = new Thread(() -> {
            executor.execute(() -> {
                u1.exec(() -> {
                    u1.attack(u2, 1);
                });
            });
        });
        Thread t3 = new Thread(() -> {
            executor.execute(() -> {
                u2.exec(() -> {
                    u2.attack(u1, 1);
                    u2.incrHp(1);
                });
            });
        });
        Thread t4 = new Thread(() -> {
            executor.execute(() -> {
                u2.exec(() -> {
                    u2.attack(u1, 1);
                    u2.incrHp(1);
                });
            });
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        latch.await();
        // stopWatch.stop();
        // Console.log(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));

        executor.shutdown();
        for (ExecutorService executorService : userExecutor) {
            executorService.shutdown();
        }

        int u1Exp = u1.getExp();
        if(u1Exp != 2){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，u1经验[exp="+u1Exp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，u1经验[exp="+u1Exp+"]");
        }

        int u2Hp = u2.getHp();
        if(u2Hp != 100){
            throw new RuntimeException("第"+i+"次u1和u2互相攻击失败，u2血量[hp="+u2Hp+"]");
        }else{
            System.out.println("第"+i+"次u1和u2互相攻击成功，u2血量[hp="+u2Hp+"]");
        }
    }

}
