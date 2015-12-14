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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (address != null ? !address.equals(person.address) : person.address != null) return false;
        if (!name.equals(person.name)) return false;
        if (!sname.equals(person.sname)) return false;
        if (telehone != null ? !telehone.equals(person.telehone) : person.telehone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + sname.hashCode();
        result = 31 * result + (telehone != null ? telehone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
