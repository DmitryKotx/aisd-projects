package com.company;

import java.util.*;

import java.util.NoSuchElementException;

public class myQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        private Node(T value) {
            this(value, null);
        }

        private T getValue() {
            return value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        private Node<T> getNext() {
            return next;
        }

        private void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public myQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T value) {
        Node n = new Node(value);
        if (isEmpty()) {
            head = n;
        } else {
            tail.setNext(n);
        }
        tail = n;
        size++;
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T val = head.getValue();
        head = head.getNext();
        size--;
        return val;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T val = head.getValue();
        head = head.getNext();
        size--;
        return val;
    }

    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getValue();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return head.getValue();
    }
}
/*3 поля для вывода
класс карта
тип Т
countStep
 */

