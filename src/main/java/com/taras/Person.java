package com.taras;

/**
 * Created by taras on 28.10.2015.
 */

public class Person {
    String name;
    String sname;
    String telehone;
    String address;
    int counter;

     private Person(String name, String sname, String telephone, String address){
        this.name = name;
        this.sname = sname;
        this.telehone = telephone;
        this.address = address;
        counter = 0;
    }

    static Person createPerson(String name, String sname, String telephone, String address) {
        Person temp = new Person(name, sname, telephone, address);
        PersonManager.addPerson(temp);
        return temp;
    }

    @Override
    public String toString() {
        return name + "_" + sname;
    }

//    @Override
//    public int hashCode() {
//        return name.length();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj.hashCode() == this.hashCode())
//            return true;
//        return false;
//    }
}
