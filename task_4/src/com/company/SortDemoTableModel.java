package com.company;

import javax.swing.table.AbstractTableModel;

public class SortDemoTableModel extends AbstractTableModel implements FrameMain.StateViewer {
    private SortState state = null;
    public SortState getState() {
        return state;
    }
    public int getRowCount() {
        return 1;
    }
    public int getColumnCount() {
        if (state == null)
            return 0;
        return state.getArr().length;
    }
        public Object getValueAt ( int r, int c){
            if (state == null)
                return null;
            return state.getArr()[c];
        }
        public String getColumnName (int col){
            return String.format("[%d]", col);
        }
        public Class getColumnClass ( int col){
            return Integer.class;
        }
        public void show (SortState ss){
            state = ss;
            fireTableStructureChanged();
        }
    }

