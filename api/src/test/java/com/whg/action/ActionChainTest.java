package com.whg.action;

import java.util.concurrent.TimeUnit;

public class ActionChainTest {

    public static void main(String[] args) throws InterruptedException {
        ActionExecutor executor = ActionExecutors.newFixed("T", 1);
        ActionQueue queue = new ActionQueue(executor);
        ActionChain chain = new ActionChain();

        chain.append(new Action(queue) {
            @Override
            public void exec() {
                System.out.println("Ni Hao!!!");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        chain.append(new Action(queue) {
            @Override
            public void exec() {
                System.out.println("Say Hello!!!");
            }
        });

        chain.append(new Action(queue) {
            @Override
            public void exec() {
                System.out.println("Hello 3!!!");
            }
        });

        chain.checkin();

        TimeUnit.SECONDS.sleep(10);
        executor.shutdown();
    }

}
