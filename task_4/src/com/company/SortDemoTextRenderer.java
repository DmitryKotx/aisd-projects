package com.company;

import com.util.JTableUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SortDemoTextRenderer {
    public static void textRenderer(List<SortState> list, int k, JTable result, JTextField compare1, JTextField compare2, JTextField change1, JTextField change2, JTextField textInserted, JTextField text_I, JTextField text_J) {
        JTableUtils.writeArrayToJTable(result, list.get(k).getArr());
        if (list.get(k).getType() == SortState.Type.Compare) {
            compare1.setText(Integer.toString(list.get(k).getValue()));
            compare2.setText(Integer.toString(list.get(k).getArr()[list.get(k).getJ()]));
            compare1.setBackground(Color.YELLOW);
            compare2.setBackground(Color.CYAN);
            textInserted.setText(Integer.toString(list.get(k).getValue()));
            textInserted.setBackground(Color.YELLOW);
        }
        if (list.get(k).getType() == SortState.Type.Change) {
            change1.setText(Integer.toString(list.get(k).getArr()[list.get(k).getJ()]));
            change2.setText(Integer.toString(list.get(k).getArr()[list.get(k).getJ()+1]));
            change1.setBackground(Color.GREEN);
            change2.setBackground(Color.RED);
        }

        text_I.setText(Integer.toString(list.get(k).getI()));
        text_J.setText(Integer.toString(list.get(k).getJ()));

    }
}
