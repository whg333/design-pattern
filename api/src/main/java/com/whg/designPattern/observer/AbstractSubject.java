package com.whg.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject implements Subject{

    protected List<Observer> observers;

    public AbstractSubject(){
        observers = new ArrayList<>();
    }

    @Override
    public boolean addObserver(Observer observer){
        if(observers.contains(observer)){
            return false;
        }
        return observers.add(observer);
    }

    @Override
    public boolean delObserver(Observer observer){
        return observers.remove(observer);
    }

    @Override
    public void notifyObservers(){
        for (Observer observer:observers){
            observer.update(this);
        }
    }

}
