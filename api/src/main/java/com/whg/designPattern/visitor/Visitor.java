package com.whg.designPattern.visitor;

import com.whg.designPattern.composite.Directory;
import com.whg.designPattern.composite.File;

/**
 * 访问者，定义了访问各种不同Element元素的方法
 */
public interface Visitor {

    void visitFile(File file);

    void visitDirectory(Directory directory);

}
