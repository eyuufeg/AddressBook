package com.ericsson.javatraining.data;

public class PhoneModule {

    private String name, number, address;

    public PhoneModule() {

    }

    public PhoneModule(String line) {
        String[] strs = line.split("::");
        name = strs[0];
        number = strs[1];
        address = strs[2];
    }

    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    public String setNumber(String number) {
        this.number = number;
        return this.number;
    }

    public String setAddress(String address) {
        this.address = address;
        return this.address;
    }

    public String getName() {

        return name;
    }

    public String getNumber() {

        return number;
    }

    public String getAddress() {

        return address;
    }
    public String toString() {
        return String.format("-- %s\r\n-- %s\r\n-- %s", name, number, address);
    }

}
