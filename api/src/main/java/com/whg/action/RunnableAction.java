package com.whg.action;

// @Deprecated
// Action具体逻辑能写在exec感觉就没有必要有runnable属性变量在exec执行run了，确实也没在项目中使用
public final class RunnableAction extends Action {

    private final Runnable runnable;

    public RunnableAction(ActionQueue queue, Runnable runnable) {
        super(queue);
        this.runnable = runnable;
    }

    @Override
    protected final void exec() {
        runnable.run();
    }

    public static final RunnableAction of(ActionQueue queue, Runnable runnable) {
        return new RunnableAction(queue, runnable);
    }

}
