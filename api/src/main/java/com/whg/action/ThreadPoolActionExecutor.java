package com.whg.action;

import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ThreadPoolActionExecutor implements ActionExecutor {

    private final String name;
    private final ActionQueue defaultQueue;
    private final ThreadPoolExecutor executor;
    private final ThreadsFactory threadsFactory;

    private boolean isRunning = true;
    // delay load
    private DelayCheckThread delayCheckThread;

    /**
     * 执行action队列的线程池
     * 
     * @param corePoolSize
     *            最小线程数
     * @param maxPoolSize
     *            最大线程数
     * @param name
     *            线程名
     */
    ThreadPoolActionExecutor(final int corePoolSize, final int maxPoolSize, final String name, List<ActionExecutor> executors) {
        this.name = name == null ? "customer" : name;
        // 超出corePoolSize数量之后的线程 超过5分钟空闲将被回收
        int keepAliveTime = 5;
        TimeUnit unit = TimeUnit.MINUTES;

        final LinkedTransferQueue<Runnable> workQueue = new LinkedTransferQueue<Runnable>();
        final RejectedExecutionHandler handler = (r, e) -> {
        };

        threadsFactory = new ThreadsFactory(this.name);

        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, threadsFactory, handler);
        defaultQueue = new ActionQueue(this);

        executors.add(this);
    }

    @Override
    public ActionQueue defaultQueue() {
        return defaultQueue;
    }

    @Override
    public void delayCheck(DelayAction action) {
        if (this.delayCheckThread == null) {
            configureDelayCheckTread();
        }

        delayCheckThread.checkin(action);
    }

    private synchronized void configureDelayCheckTread() {
        if (this.delayCheckThread == null) {
            this.delayCheckThread = new DelayCheckThread(name);
            this.delayCheckThread.start();
        }
    }

    @Override
    public void execute(Runnable action) {
        executor.execute(action);
    }

    @Override
    public synchronized void shutdown() {
        if (isRunning) {
            if (!executor.isShutdown()) {
                executor.shutdown();
            }

            if (delayCheckThread != null)
                delayCheckThread.stopping();

            isRunning = false;
        }
    }

    public int getWaitingActionsCount() {
        return executor.getQueue().size();
    }

    public String getName() {
        return name;
    }

    public long getCompletedActionsCount() {
        return executor.getCompletedTaskCount();
    }

    public int getActiveThreadsCount() {
        return executor.getActiveCount();
    }

    public int getPooledThreadsCount() {
        return executor.getPoolSize();
    }

    public int getMaxTheadsCount() {
        return executor.getMaximumPoolSize();
    }

    public int getCoreThreadsCount() {
        return executor.getCorePoolSize();
    }
}
