package com.whg.game;

public class GameUserTest {

    public static void main(String[] args) throws Exception {
        for(int i=1;i<=1000;i++){
            // test1(i);
            // test2(i);
            // test3(i);
            test4(i);
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

}
