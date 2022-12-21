package com.whg.designPattern.decorator;

public class UpDownBorder extends SideBorder{
    public UpDownBorder(Display display, char ch) {
        super(display, ch);
    }

    @Override
    public int getColumns() {
        return display.getColumns();
    }

    @Override
    public int getRows() {
        return 1 + display.getRows() + 1;
    }

    @Override
    public String getRowText(int row) {
        if(row == 0 || row == display.getRows()+1){
            return makeLine(borderChar, display.getColumns());
        }else{
            return display.getRowText(row-1);
        }
    }
}
