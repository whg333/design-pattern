package com.whg.designPattern.composite;

import com.whg.designPattern.visitor.Element;

public abstract class Entry implements Element {

    @Override
    public String toString() {
        return getName() + " (" + getSize() + ")";
    }

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry){
        throw new UnsupportedOperationException();
    }

    public void printList(){
        printList("");
    }

    protected abstract void printList(String prefix);

}
