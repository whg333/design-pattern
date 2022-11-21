package com.whg.designPattern.visitor;

/**
 * 可以被访问者访问的元素
 */
public interface Element {

    // 接受访问者的访问请求
    void accept(Visitor visitor);

}
