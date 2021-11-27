package com.whg.action;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 动作执行器，包括一组静态的ActionExecutor列表
 * @date 2018年3月4日 下午2:22:49
 * @see ThreadPoolActionExecutor
 * @see ActionExecutor
 */
public class ActionExecutors {

    private final static List<ActionExecutor> executors = new ArrayList<ActionExecutor>();

    public static ActionExecutor newSingle(String name) {
        return new ThreadPoolActionExecutor(1, 1, name, executors);
    }

    public static ActionExecutor newFixed(String name, int nThreads) {
        return new ThreadPoolActionExecutor(nThreads, nThreads, name, executors);
    }

    /**
     * @param name
     * @param nThreads,
     *            maxThreads use double nThreads
     * @return
     */
    public static ActionExecutor newCached(String name, int nThreads) {
        return newCached(name, nThreads, nThreads * 3);
    }

    public static ActionExecutor newCached(String name, int nThreads, int maxThreads) {
        return new ThreadPoolActionExecutor(maxThreads, maxThreads, name, executors);
    }

    public static void shutdown() {
        for (ActionExecutor executor : executors) {
            executor.shutdown();
        }
    }

    public static List<ActionExecutor> getExecutors() {
        return executors;
    }
}
