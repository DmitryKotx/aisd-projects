package com.company;


import java.util.ArrayList;
import java.util.List;

public class constructorClass {
     private String access = "";
    private List<parameterClass> paramC = new ArrayList<>();

    public  String getAccess () {
        return access;
    }
    public  void setAccess (String line) {
        access = line;
    }

    public void addPC () {
        paramC.add(new parameterClass("",""));
    }
    public parameterClass getPC(int i) {
        return paramC.get(i);
    }
    public int sizePC () {
        return paramC.size();
    }
    public void removePC (int i) {
        paramC.remove(i);
    }
}