package com.whg.action;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc 动作——即可执行单元（runnable）的封装
 *       <p>
 *       需传入执行队列ActionQueue，知晓等待的waitings数目， 还有加入队列checkin和移除队列checkout等
 *       </p>
 * @date 2018年3月4日 下午2:29:50
 */
public abstract class Action implements Runnable, Sequential {

    protected static final Logger logger = LoggerFactory.getLogger(Action.class);

    protected final ActionQueue queue;
    protected long createTime;

    public Action(ActionQueue queue) {
        this.queue = queue;
        this.createTime = System.currentTimeMillis();
    }

    public ActionQueue getActionQueue() {
        return queue;
    }

    public void checkin() {
        queue.checkin(this);
    }

    @Override
    public final void run() {
        try {
            if (runable()) {
                long createTime = this.createTime;
                long start = System.currentTimeMillis();
                exec();
                long end = System.currentTimeMillis();
                Monitor.log(getClazz(), createTime, start, end, this);
            }
        } catch (Throwable e) {
            logger.error("Execute exception: " + getClazz().getName(), e);
        } finally {
            queue.checkout(this);
            done();
        }
    }

    protected abstract void exec();

    protected void done() {
        // do nothing
    }

    protected boolean runable() {
        return true;
    }

    protected Class<?> getClazz() {
        return getClass();
    }

    @Override
    public final int waitings() {
        return queue.size();
    }

    @Override
    public String toString() {
        return getClazz().getName() + " [" + DateTimeFormatter.ofPattern("MM-dd HH:mm:ss").format(ZonedDateTime.now()) + "]";
    }

}
