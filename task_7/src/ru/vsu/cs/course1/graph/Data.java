package ru.vsu.cs.course1.graph;

import java.util.Map;

public class Data {
    private Map<Integer, Info> map;
    private int maxLength;
    private String maxPath;

    public Data(Map<Integer, Info> map, int maxLength, String maxPath) {
        this.map = map;
        this.maxLength = maxLength;
        this.maxPath = maxPath;
    }

    public Map<Integer, Info> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Info> map) {
        this.map = map;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getMaxPath() {
        return maxPath;
    }

    public void setMaxPath(String maxPath) {
        this.maxPath = maxPath;
    }
}
