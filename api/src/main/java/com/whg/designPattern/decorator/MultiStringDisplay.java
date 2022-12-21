package com.whg.designPattern.decorator;

import java.util.ArrayList;
import java.util.List;

public class MultiStringDisplay extends Display{

    private final List<String> strList = new ArrayList<>();
    private int maxSize;

    public void add(String str){
        strList.add(str);
        int size = str.getBytes().length;
        if(size > maxSize){
            maxSize = size;
        }
    }

    @Override
    public int getColumns() {
        return maxSize;
    }

    @Override
    public int getRows() {
        return strList.size();
    }

    @Override
    public String getRowText(int row) {
        StringBuilder sb = new StringBuilder(strList.get(row));
        int size = sb.toString().getBytes().length;
        for(int i=0;i<maxSize-size;i++){
            sb.append(' ');
        }
        return sb.toString();
    }
}
