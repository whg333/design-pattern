package com.whg.action;

/**
 * @desc 动作执行器，用于执行（execute）动作（action——runnable）
 * @author luzj
 * @date 2018年3月4日 下午2:33:30
 * @see ActionExecutors
 */
public interface ActionExecutor {

    public ActionQueue defaultQueue();

    public void delayCheck(DelayAction action);

    public void execute(Runnable action);

    public void shutdown();

}
