package com.whg.designPattern.adapter;

/** 使用类继承 */
public class PrintBanner extends Banner implements Print{
    public PrintBanner(String str) {
        super(str);
    }
    @Override
    public void printWeak() {
        showWithParen();
    }
    @Override
    public void printStrong() {
        showWithAster();
    }
}
