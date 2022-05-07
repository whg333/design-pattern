package com.whg.designPattern.composite;

import com.whg.designPattern.visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory extends Entry{

    private final String name;
    private final List<Entry> entries;

    public Directory(String name) {
        this.name = name;
        entries = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return entries.stream().map(Entry::getSize).reduce(0, Integer::sum);
    }

    @Override
    public Entry add(Entry entry) {
        entries.add(entry);
        return this;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        entries.forEach(entry ->  entry.printList(prefix + "/" + name));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDirectory(this);
    }

    public Iterator<Entry> iterator(){
        return entries.iterator();
    }

}
