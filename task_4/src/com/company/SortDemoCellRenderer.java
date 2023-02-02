package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class SortDemoCellRenderer {
    public static void cellRenderer(List<SortState> list, int k, JTable result) {
        TableCellRenderer renderer;
        if (k == list.size()-1 || k == 0) {
            Color color = k == list.size() - 1 ? Color.GREEN : Color.GRAY;
            for (int i = 0; i < list.get(k).getArr().length; i++) {
                renderer = new DefaultTableCellRenderer();
                Object value = result.getValueAt(0, i);
                result.getColumnModel().getColumn(i).setCellRenderer(renderer);
                JLabel c = (JLabel) renderer.getTableCellRendererComponent(result, value, true, false, 0, i);
                c.setBackground(color);
            }
            return;
        }
        for (int i = list.get(k).getLeft(); i < list.get(k).getRight(); i++) {
            renderer = new DefaultTableCellRenderer();
            Object value = result.getValueAt(0, i);
            result.getColumnModel().getColumn(i).setCellRenderer(renderer);
            JLabel c = (JLabel) renderer.getTableCellRendererComponent(result, value, true, false, 0, i);
            c.setBackground(Color.GRAY);
        }
        if (check(list, list.get(k).getI())) {
            for (int i = 0; i < list.get(k).getI(); i++) {
                renderer = new DefaultTableCellRenderer();
                Object value = result.getValueAt(0, i);
                result.getColumnModel().getColumn(i).setCellRenderer(renderer);
                JLabel c = (JLabel) renderer.getTableCellRendererComponent(result, value, true, false, 0, i);
                c.setBackground(Color.GREEN);
            }
        }
        renderer = new DefaultTableCellRenderer();
        int col = list.get(k).getJ() + 1;
        Object value = result.getValueAt(0, col);
        result.getColumnModel().getColumn(col).setCellRenderer(renderer);
        JLabel c = (JLabel) renderer.getTableCellRendererComponent(result, value, true, false, 0, col);
        c.setBackground(Color.YELLOW);
        if (col > 0) {
            if (list.get(k).getArr()[col] < list.get(k).getArr()[col - 1]) {
                c.setBackground(Color.YELLOW);

            } else {
                c.setBackground(Color.RED);
                renderer = new DefaultTableCellRenderer();
                Object value1 = result.getValueAt(0, col - 1);
                result.getColumnModel().getColumn(col - 1).setCellRenderer(renderer);
                JLabel g = (JLabel) renderer.getTableCellRendererComponent(result, value1, true, false, 0, col - 1);
                g.setBackground(Color.CYAN);
            }
        }
    }
    public static boolean check (List<SortState> list, int col) {
        for (int i = 1; i <= col; i++) {
            if (list.get(i).equals(list.get(i-1))) {
                return false;
            }
        }
        return true;
    }
}
