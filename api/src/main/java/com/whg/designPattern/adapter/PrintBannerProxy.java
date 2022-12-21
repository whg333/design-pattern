package com.whg.designPattern.adapter;

/** 使用委托代理 */
public class PrintBannerProxy implements Print{
    private final Banner banner;
    public PrintBannerProxy(String str) {
        this.banner = new Banner(str);
    }
    @Override
    public void printWeak() {
        banner.showWithParen();
    }
    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
