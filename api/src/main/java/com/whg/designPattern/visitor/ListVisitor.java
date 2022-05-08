package com.whg.designPattern.visitor;

import com.whg.designPattern.composite.Directory;
import com.whg.designPattern.composite.Entry;
import com.whg.designPattern.composite.File;
import com.whg.designPattern.iterator.Iterator;

public class ListVisitor implements Visitor{

    private String currentDir = "";

    @Override
    public void visitFile(File file) {
        System.out.println(currentDir + "/" + file);
    }

    @Override
    public void visitDirectory(Directory directory) {
        System.out.println(currentDir + "/" + directory);

        String saveDir = currentDir;
        currentDir = currentDir + "/" + directory.getName();
        Iterator<Entry> it = directory.iterator();
        while (it.hasNext()){
            Entry entry = it.next();
            entry.accept(this);
        }
        currentDir = saveDir;
    }

}
