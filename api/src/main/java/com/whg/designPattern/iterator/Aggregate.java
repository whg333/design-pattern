package com.whg.designPattern.iterator;

/**
 * 聚合接口，代表可以聚合多个元素，能返回迭代器用于遍历聚合内的元素
 * @param <E>
 */
public interface Aggregate<E> {

    Iterator<E> iterator();

}
