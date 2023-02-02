package com.company;



public class fieldClass {
     private String access = "";
     private String type = "";
     private String name = "";
     private String value = "";
     private String modS = "";
     private String modF = "";
     public fieldClass (String name, String type, String access, String value) {
         this.name =name;
         this.type = type;
         this.access = access;
         this.value = value;
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
    public  String getValue () {
        return value;
    }
    public  void setValue (String line) {
        value = line;
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



}