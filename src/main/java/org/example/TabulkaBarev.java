package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TabulkaBarev extends AbstractTableModel {
    private String[] columnNames = {"Bavra", "Red", "Green", "Blue"};

    private List<Barva> barvy;

    public TabulkaBarev(List<Barva> barvy) {
        this.barvy = barvy;
    }


    @Override
    public int getRowCount() {
        return barvy.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Barva barva = barvy.get(rowIndex);
        switch (columnIndex) {
            case 0: return barva.getNazev();
            case 1: return barva.getRed();
            case 2: return barva.getGreen();
            case 3: return barva.getBlue();
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
