package com.whg.designPattern.iterator;

import com.whg.designPattern.composite.Directory;
import com.whg.designPattern.composite.Entry;
import com.whg.designPattern.composite.File;

public class Main {

    public static void main(String[] args) {
        Directory bookShelf = new Directory("bookShelf");
        bookShelf.add(new File("Around the World in 80 Days"));
        bookShelf.add(new File("Bible"));
        bookShelf.add(new File("Cinderella"));
        bookShelf.add(new File("Daddy-Long-Legs"));

        Iterator<Entry> it = bookShelf.iterator();
        while (it.hasNext()){
            Entry book = it.next();
            System.out.println(book.getName());
        }
    }

}
