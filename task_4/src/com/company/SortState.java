package com.company;

import java.util.Arrays;

public class SortState {
    public enum Type {State, Compare, Change}
    private Type type; /* Тип состояния */
    private int[] arr; /* Массив */
    private int left, right; /* Левая и правая граница */
    private int i, j; /* Индексы активных элементов */
    private int value;
    public SortState(Type type, int[] array, int value, int left, int right, int i, int j) {
        this. type = type;
        this.arr = Arrays.copyOf(array, array.length);
        this.value = value;
        this. left = left;
        this. right = right;
        this.i = i;
        this.j = j;
    }
    public Type getType() { return type; }
    public int[] getArr() { return arr; }
    public int getValue() { return value; }
    public int getLeft() { return left; }
    public int getRight() { return right; }
    public int getI() { return i; }
    public int getJ() { return j; }
}
