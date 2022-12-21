package com.whg.designPattern.responsibility;

public abstract class Handler {

    private final String name;
    private Handler next;

    public Handler(String name) {
        this.name = name;
    }

    public Handler setNext(Handler next){
        this.next = next;
        return next;
    }

    // 模板方法模式
    public final void handle(Mission mission){
        if(resolve(mission)){
            completed(mission);
        }else if(next != null){
            next.handle(mission);
        }else{
            failed(mission);
        }
    }

    public abstract boolean resolve(Mission mission);

    protected void completed(Mission mission){
        System.out.println(mission+" completed by "+this);
    }

    protected void failed(Mission mission) {
        System.out.println(mission+" cannot be resolved");
    }

    @Override
    public String toString() {
        return "Handler{" +
                "name='" + name + '\'' +
                '}';
    }
}
