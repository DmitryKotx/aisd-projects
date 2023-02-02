package com.company;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    public static List<SortState> sort(int[] data) {
        List<SortState> list = new ArrayList<>();
        list.add(new SortState(SortState.Type.State, data, 0,data.length, data.length, 0,0));
        for (int left = 0; left < data.length; left++) {
            int value = data[left];
            int j = left - 1;
            for (; j >= 0; j--) {
                list.add(new SortState(SortState.Type.Compare, data,value, left, data.length, left, j));
                if (value < data[j]) {
                    list.add(new SortState(SortState.Type.Change, data,value, left, data.length, left, j));
                    data[j + 1] = data[j];

                } else {
                    break;
                }
            }
            data[j + 1] = value;
            list.add(new SortState(SortState.Type.State, data,0, left, data.length, left, j));
        }
        list.add(new SortState(SortState.Type.State, data, 0,data.length, data.length, 0,0));
        return list;
     }
}
