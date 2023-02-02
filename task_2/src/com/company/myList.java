package com.company;

public class myList {
    static class Node {
        private int value;
        private Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;

        }

        public Node(int value) {
            this(value, null);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;


    public myList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(int val) {
        Node n = new Node(val, head);
        if (isEmpty()) {
            tail = n;
        }
        head = n;
        size++;
    }

    public void add(int val) {
        if (isEmpty()) {
            addFirst(val);
            return;
        }
        Node n = new Node(val);
        tail.next = n;
        tail = n;
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private Node getNode(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        if (index >= size()) {
            throw new IndexOutOfBoundsException(String.format("Index must be below %d", size()));
        }
        int counter = 0;
        Node current = head;
        while (current != null && counter < index) {
            counter++;
            current = current.getNext();
        }
        if (current == null) {
            throw new NullPointerException("List corrupted exception");
        }
        return current;
    }

    public int get(int index) {
        return getNode(index).getValue();
    }

    public void set(int index, int value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            if (index == 0) {
                addFirst(value);
            } else {
                throw new IndexOutOfBoundsException("List is empty");
            }
        }
        if (index >= size()) {
            throw new IndexOutOfBoundsException(String.format("Index must be below %d", size()));
        }
        int counter = 0;
        Node current = head;
        while (current != null && counter < index) {
            counter++;
            current = current.getNext();
        }
        if (current == null) {
            throw new NullPointerException("List corrupted exception");
        }
        current.setValue(value);
    }

    public void add(int val, int index) {
        if (index == 0) {
            addFirst(val);
        } else if (index == size()) {
            add(val);
        } else {
            Node previous = getNode(index - 1);
            Node n = new Node(val, previous.getNext());
            previous.setNext(n);
            size++;
        }
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        head = head.getNext();
        size--;
    }

    public void remove (int index) {
        if (index == 0) {
            removeFirst();
        } else {
            Node n = getNode(index);
            Node previous = getNode(index-1);
            previous.setNext(n.getNext());
            if (index == size()-1) {
                tail.next = previous;
                tail = previous;
            }
            size--;
        }
    }

    public String asString() {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (current != null) {
            sb.append(current.getValue()).append(current.next == null ? "" : ", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public void reverseRec() {
        if (isEmpty()) {
            return;
        }
        reverse(head);
        Node node = head;
        head = tail;
        tail = node;
    }
    private void reverse(Node node) {
        if (node.next == null) {
            return;
        }
        reverse(node.next);
        node.next.next = node;
        node.next = null;
    }
}