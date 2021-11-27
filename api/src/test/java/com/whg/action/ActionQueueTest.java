package com.whg.action;

public class ActionQueueTest {

    public static void main(String[] args) {
        // ActionExecutor executor = ActionExecutors.newCached("Test", 1, 5);
        ActionExecutor executor = ActionExecutors.newSingle("Test");
        for (int i = 0; i < 10; i++) {
            final int j = i;
            ActionQueue actionQueue = new ActionQueue(executor);
            (new Action(actionQueue) {
                @Override
                protected void exec() {
                    System.out.println(j);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).checkin();
        }
        executor.shutdown();
    }

}
