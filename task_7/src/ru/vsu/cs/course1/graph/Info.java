package ru.vsu.cs.course1.graph;


public class Info {
    private int length;
    private String path;
    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public Info(int length, String path) {
        this.length = length;
        this.path = path;
    }

    public int getLength() {
        return length;
    }
}

