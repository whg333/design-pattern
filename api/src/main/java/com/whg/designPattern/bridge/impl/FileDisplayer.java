package com.whg.designPattern.bridge.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class FileDisplayer implements Displayer{

    private String filePath;
    private FileInputStream fis;
    private String content;

    public FileDisplayer(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void doOpen() {
        printLine();
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPrint() {
        if(content == null){
            byte[] bytes = null;
            try {
                int size = fis.available();
                bytes = new byte[size];
                fis.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(bytes);
            content = new String(bytes);
        }
        System.out.println(content);
    }

    @Override
    public void doClose() {
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printLine();
    }

    private void printLine() {
        System.out.print("+");
        for(int i=0;i<50;i++){
            System.out.print("-");
        }
        System.out.println("+");
    }
    //end
}
