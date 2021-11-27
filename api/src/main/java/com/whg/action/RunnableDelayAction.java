package com.whg.action;

// @Deprecated
// Action具体逻辑能写在exec感觉就没有必要有runnable属性变量在exec执行run了，确实也没在项目中使用
public class RunnableDelayAction extends DelayAction {

    private final Runnable runnable;

    public RunnableDelayAction(ActionQueue queue, int delay, Runnable runnable) {
        super(queue, delay);
        this.runnable = runnable;
    }

    @Override
    protected void exec() {
        runnable.run();
    }

}
