package com.whg.test;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformanceTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        Map<Integer, Integer> map = new HashMap<>(list.size());
        for(Integer integer:list){
            map.put(integer, integer);
        }
        // list.forEach(integer -> {
        //     map.put(integer, integer);
        // });
        System.out.println(map);
    }

}
