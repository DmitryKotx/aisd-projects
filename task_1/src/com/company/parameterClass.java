package com.company;


public class parameterClass {

     private String type = "";
     private String name = "";
     private String modF = "";


     public parameterClass(String name, String type) {
         this.name = name;
         this.type = type;
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
    public  String getModF() {
        return modF;
    }
    public  void setModF(String line) {
        modF = line;
    }
}

