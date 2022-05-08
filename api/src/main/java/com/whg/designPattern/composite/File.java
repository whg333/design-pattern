package com.whg.designPattern.composite;

import com.whg.designPattern.visitor.Visitor;

public class File extends Entry{

    private final String name;
    private final int size;

    public File(String name){
        this(name, 10000);
    }

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitFile(this);
    }

}
