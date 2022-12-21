package com.whg.designPattern.proxy;

public class Printer implements Printable{

    private String name;

    public Printer(String name){
        setName(name);
        heavyJob();
    }

    private void heavyJob(){
        System.out.println("开始生成Printer的实例");
        for(int i=0;i<5;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
        System.out.println("\n结束生成Printer的实例");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print(String str) {
        System.out.println(this+" print:"+str);
    }

    @Override
    public String toString() {
        return "Printer{" +
                "name='" + name + '\'' +
                '}';
    }
}
