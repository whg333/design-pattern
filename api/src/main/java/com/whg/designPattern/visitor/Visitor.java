package com.whg.designPattern.visitor;

import com.whg.designPattern.composite.Directory;
import com.whg.designPattern.composite.File;

public interface Visitor {

    void visitFile(File file);

    void visitDirectory(Directory directory);

}
