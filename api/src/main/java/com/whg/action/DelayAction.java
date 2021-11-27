package com.whg.action;

public abstract class DelayAction extends Action {

    long execTime;
    volatile boolean isCancelled;

    public DelayAction(ActionQueue queue, int delay) {
        super(queue);
        initialize(createTime, delay);
    }

    public DelayAction(ActionQueue queue, long curTime, int delay) {
        super(queue);
        initialize(curTime, delay);
    }

    private void initialize(long curTime, int delay) {
        this.isCancelled = false;
        this.createTime = curTime;
        this.execTime = delay > 0 ? (curTime + delay) : 0;
    }

    @Override
    protected boolean runable() {
        return !isCancelled;
    }

    @Override
    public void checkin() {
        if (execTime <= 0) {// don`t need delay
            queue.checkin(this);
        } else {
            queue.checkinDelayAction(this);
        }
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public void recheckin(int delay) {
        this.recheckin(System.currentTimeMillis(), delay);
    }

    public void recheckin(long curTime, int delay) {
        initialize(curTime, delay);
        checkin();
    }

    public boolean tryExec(long curTime) {
        if (isCancelled) {
            return true;
        }
        if (curTime < execTime) {
            return false;
        }
        createTime = curTime;
        getActionQueue().checkin(this);
        return true;
    }

    public static final DelayAction of(ActionQueue queue, int delay, Runnable runnable) {
        return new DelayAction(queue, delay) {
            @Override
            protected void exec() {
                runnable.run();
            }
        };
    }

}
