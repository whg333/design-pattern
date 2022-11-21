package com.whg.designPattern.iterator;

/**
 * 迭代器，定义遍历聚合中元素的方法
 * @param <E>
 */
public interface Iterator<E> {

    boolean hasNext();

    E next();

}
