package com.company;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    public static List<SortState> sort(int[] data) {
        for (int left = 0; left < data.length; left++) {
            int value = data[left];
            int j = left - 1;
            for (; j >= 0; j--) {
                if (value < data[j]) {
                    data[j + 1] = data[j];
                } else {
                    break;
                }
            }
            data[j + 1] = value;
        }
        return list;
     }
}
