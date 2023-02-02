package com.company;

import java.util.ArrayList;
import java.util.List;

public class myClass {
    private List<fieldClass> field = new ArrayList<>();
    private List<constructorClass> constructor = new ArrayList<>();
    private List<methodClass> method = new ArrayList<>();

    public fieldClass getF(int i) {
        return field.get(i);
    }
    public void addF () {
        field.add(new fieldClass("","","",""));
    }
    public int sizeF () {
        return field.size();
    }
    public void removeF (int i) {
        field.remove(i);
    }

    public void addM () {
        method.add(new methodClass("","",""));
    }
    public methodClass getM(int i) {
        return method.get(i);
    }
    public int sizeM () {
        return method.size();
    }
    public void removeM (int i) {
        method.remove(i);
    }

    public void addC () {
        constructor.add(new constructorClass());
    }
    public constructorClass getC(int i) {
        return constructor.get(i);
    }
    public int sizeC () {
        return constructor.size();
    }
    public void removeC (int i) {
        constructor.remove(i);
    }

    public void addPC (int i) {
        constructor.get(i).addPC();
    }
    public parameterClass getPC (int i, int j) {
        return constructor.get(i).getPC(j);
    }
    public int sizePC (int i) {
        return constructor.get(i).sizePC();
    }
    public void removePC (int i, int j) {
        constructor.get(i).removePC(j);
    }

    public void addPM (int i, parameterClass val) {
        method.get(i).addPM(val);
    }
    public parameterClass getPM (int i, int j) {
        return method.get(i).getPM(j);
    }
    public int sizePM (int i) {
        return method.get(i).sizePM();
    }
    public void removePM (int i, int j) {
        method.get(i).removePM(j);
    }

}

