package com.taras;


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
        char semicolon = ';';
        String result = new StringBuilder(name)
                .append(semicolon)
                .append(sname)
                .append(semicolon)
                .append(telehone)
                .append(semicolon)
                .append(address).toString();
        return result;
    }

    @Override
    public int hashCode() {
        return name.length() + sname.length();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person temp = (Person)obj;
            if ((this.name.equals(temp.name))
                    && (this.sname.equals(temp.sname)))
                return true;
        }
        return false;
    }
}
