package com.whg.action;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @desc 动作队列，用于排队(FIFO)执行一系列动作
 * @date 2018年3月4日 下午2:13:51
 * @see Action
 */
public class ActionQueue {

    private final ActionExecutor executor;
    private final ConcurrentLinkedQueue<Runnable> queue;
    private AtomicBoolean isRunning;

    public ActionQueue(ActionExecutor executor) {
        this.executor = executor;
        queue = new ConcurrentLinkedQueue<>();
        isRunning = new AtomicBoolean(false);
    }

    void checkinDelayAction(DelayAction action) {
        executor.delayCheck(action);
    }

    void checkin(Action action) {
        queue.offer(action);

        if (isRunning.compareAndSet(false, true)) {
            execNext();
        }
    }

    void checkout(Runnable action) {
        queue.poll();
        execNext();
    }

    private Runnable execNext() {
        Runnable next = queue.peek();
        if (next != null) {
            executor.execute(next);
        } else {
            isRunning.set(false);

            // double check
            next = queue.peek();
            if (next != null && isRunning.compareAndSet(false, true)) {
                executor.execute(next);
            }
        }
        return next;
    }

    int size() {
        return queue.size();
    }

}
