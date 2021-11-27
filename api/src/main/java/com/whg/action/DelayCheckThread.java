package com.whg.action;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc 延迟执行线程，用于执行延迟动作DelayAction
 * @date 2018年3月4日 下午3:19:58
 */
class DelayCheckThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ActionExecutor.class);

    private static final int ZIZZ_TIME = 40;// ms
    private ConcurrentLinkedDeque<DelayAction> queue;
    private boolean isRunning; // TODO 添加volatile修饰以便在run的while里停止

    public DelayCheckThread(String prefix) {
        super(prefix + "-thread-dc");
        queue = new ConcurrentLinkedDeque<>();
        isRunning = true;
        setPriority(Thread.MAX_PRIORITY); // 给予高优先级
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopping() {
        if (isRunning) {
            isRunning = false;
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                long zizzTime = ZIZZ_TIME;
                if (!queue.isEmpty()) {
                    long start = System.currentTimeMillis();
                    int checked = checkActions();
                    long interval = System.currentTimeMillis() - start;
                    zizzTime -= interval;
                    if (interval > ZIZZ_TIME) {
                        logger.warn(getName() + " is spent too much time: " + interval + "ms, checked num = " + checked);
                    }
                }

                if (zizzTime > 0) {
                    TimeUnit.MILLISECONDS.sleep(zizzTime);
                }
            } catch (Exception e) {
                logger.error(getName() + " Error. ", e);
            }
        }
    }

    /**
     * 返回检查完成的Action数量
     **/
    private int checkActions() {
        DelayAction last = queue.peekLast();
        if (last == null) {
            return 0;
        }

        int checked = 0;
        DelayAction current = null;

        while ((current = queue.removeFirst()) != null) {
            try {
                long begin = System.currentTimeMillis();
                if (!current.tryExec(begin)) {
                    checkin(current);
                }
                checked++;
                long end = System.currentTimeMillis();
                if (end - begin > ZIZZ_TIME) {
                    logger.warn(current.toString() + "check spent too much time. time :" + (end - begin));
                }
                if (current == last) {
                    break;
                }
            } catch (Exception e) {
                logger.error("DelayAction check " + current.toString(), e);
            }
        }
        return checked;
    }

    /**
     * 添加Action到队列
     * 
     * @param delayAction
     */
    public void checkin(DelayAction delayAction) {
        queue.addLast(delayAction);
    }

}
