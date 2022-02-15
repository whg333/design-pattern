package com.whg.test;

import com.whg.util.MyUnsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Test {

    private static final AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws Exception{
        testFinal1();
        new Test().testFinal2();
    }

    private static void testFloat(){
        float f = -1.27f;
        System.out.println(Float.toHexString(f));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(f)));
    }

    private static void testSchedule(){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        AtomicLong consume = new AtomicLong(new Date().getTime());
        Runnable task = () -> {
            long now = new Date().getTime();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count.incrementAndGet();
            System.out.println("one done..."+count.get()+", consume="
                    +(now-consume.get()));
            consume.set(now);
        };

        //service.scheduleAtFixedRate(task, 2, 2, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);
    }

    private static final char[] value1 = new char[]{'a', 'b', 'c',};
    private final char[] value2 = new char[]{'d', 'e', 'f'};

    private static final boolean useReflect = true;
    private static final boolean useUnsafe = false;

    private static void testFinal1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = Test.class.getDeclaredField("value1");

        if(useReflect){
            field.setAccessible(true);

            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field,field.getModifiers()&~Modifier.FINAL);

            field.set(null, new char[]{'1', '2', '3'});
        }else if(useUnsafe){
            MyUnsafe.putObject(Test.class, MyUnsafe.staticFieldOffset(field), new char[]{'1', '2', '3'});
        }

        System.out.println(value1);
    }

    public void testFinal2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = Test.class.getDeclaredField("value2");

        if(useReflect) {
            field.setAccessible(true);
            field.set(this, new char[]{'4', '5', '6'});
        }else if(useUnsafe){
            MyUnsafe.putObject(this, MyUnsafe.objectFieldOffset(field), new char[]{'4', '5', '6'});
        }

        System.out.println(this.value2);
    }

}
