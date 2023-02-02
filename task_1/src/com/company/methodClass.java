package com.company;

import java.util.ArrayList;
import java.util.List;

public class methodClass {
     private String name = "";
     private String access = "";
     private String type = "";
    private String modS = "";
    private String modF = "";
    private List<parameterClass> paramM = new ArrayList<>();



    public methodClass (String name, String type, String access) {
        this.name =name;
        this.type = type;
        this.access = access;
    }
    public  String getName () {
        return name;
    }
    public  void setName (String line) {
        name = line;
    }
    public  String getType () {
        return type;
    }
    public  void setType (String line) {
        type = line;
    }
    public  String getAccess () {
        return access;
    }
    public  void setAccess (String line) {
        access = line;
    }
    public  String getModF () {
        return modF;
    }
    public  void setModF (String line) {
        modF = line;
    }
    public  String getModS () {
        return modS;
    }
    public  void setModS (String line) {
        modS = line;
    }

    public void addPM (parameterClass val) {
        paramM.add(val);
    }
    public parameterClass getPM(int i) {
        return paramM.get(i);
    }
    public int sizePM () {
        return paramM.size();
    }
    public void removePM (int i) {
        paramM.remove(i);
    }

}
