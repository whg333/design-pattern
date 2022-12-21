package com.whg.designPattern.observer;

public interface Subject {

    boolean addObserver(Observer observer);

    boolean delObserver(Observer observer);

    void notifyObservers();

    /** 获取主题的状态 */
    Object getStatus();

    /** 发布/订阅 的发布行为 */
    void publish();

}
