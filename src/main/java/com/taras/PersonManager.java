package com.taras;

import java.util.LinkedList;
import java.util.List;

public class PersonManager {
    final static List<Person> persons = new LinkedList<Person>();

    public static String getOftenName() {
        Person temp = persons.get(0);
        for (Person p : persons) {
            if (p.counter > temp.counter)
                temp = p;
        }
        return temp.toString();
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


    static void addPerson(Person person) {
        boolean exist = false;
        for (Person p : persons) {
            if (p.toString().equals(person.toString())) {
                p.counter++;
                exist = true;
                break;
            }
        }
        if (!exist) {
            persons.add(person);
        }
    }
}
