package com.taras;

import java.util.HashSet;
import java.util.Set;

public class PersonManager {
    private final static Set<Person> persons = new HashSet<>();

    public static String getOftenName() {
        Person temp = persons.iterator().next();
        for (Person p : persons) {
            if (p.getCounter() > temp.getCounter())
                temp = p;
        }
        return temp.toString();
    }

    public static String[] getAllText() {
        String[] res = new String[persons.size()];
        int i = 0;
        for (Person p : persons) {
            res[i] = p.toString();
            i++;
        }
        return res;
    }

    static void addPerson(Person person) {
        if (persons.contains(person)) {
            persons.remove(person);
            int personCounter = person.getCounter();
            personCounter++;
            person.setCounter(personCounter);
            persons.add(person);
        } else {
            persons.add(person);
        }
    }

    public static Set<Person> getPersons() {
        return persons;
    }
}
