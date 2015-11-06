package com.taras;

import java.util.LinkedList;

/**
 * Created by taras on 28.10.2015.
 */

public class Person {
    final static LinkedList<Person> persons = new LinkedList<Person>();
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

    public static String getOftenName() {
        Person oft = persons.getFirst();
        for (Person p : persons) {
            if (p.counter > oft.counter)
                oft = p;
        }
        return oft.toString();
    }
    public static String[] getAllText() {
        String[] res = new String[persons.size()];
        char semicolon =';';
        int i = 0;
        for (Person p : persons) {
            res[i] = new StringBuilder(p.name)
                    .append(semicolon)
                    .append(p.sname)
                    .append(semicolon)
                    .append(p.telehone)
                    .append(semicolon)
                    .append(p.address).toString();
            i++;
        }
        return res;
    }

    public static void addPerson(String name, String sname, String telephone, String address){
        boolean exist = false;
        String toString = name + "_" + sname;
        for (Person p : persons) {
            if (p.toString().equals(toString)) {
                p.counter++;
                exist = true;
            }
        }
        if (!exist) {
            persons.add(new Person(name, sname, telephone, address));
        }
    }

    @Override
    public String toString() {
        return name + "_" + sname;
    }
}
