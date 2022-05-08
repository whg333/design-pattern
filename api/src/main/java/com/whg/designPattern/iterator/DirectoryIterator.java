package com.whg.designPattern.iterator;

import com.whg.designPattern.composite.Entry;

import java.util.List;

public class DirectoryIterator implements Iterator<Entry>{

    private final List<Entry> entries;
    private int index;

    public DirectoryIterator(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public boolean hasNext() {
        return index < entries.size();
    }

    @Override
    public Entry next() {
        Entry current = entries.get(index);
        index++;
        return current;
    }

}
