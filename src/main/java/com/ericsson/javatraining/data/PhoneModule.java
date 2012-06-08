package com.ericsson.javatraining.data;

/**
 * 
 * @author eyuufeg
 * 
 */
public class PhoneModule {

    private String name, number, address;

    /**
     * default constructor
     */
    public PhoneModule() {
        super();
    }

    /**
     * constructor
     * 
     * @param String
     *            form XXX::XXX::XXX
     */
    public PhoneModule(String line) {
        super();
        String[] strs = line.split("::");
        name = strs[0];
        number = strs[1];
        address = strs[2];
    }

    /**
     * set user's name
     * 
     * @param user's name
     * @return user's name
     */
    public String setName(String name) {
        this.name = name;
        return this.name;
    }
    /**
     * set user's number
     * 
     * @param user's number
     * @return user's number
     */
    public String setNumber(String number) {
        this.number = number;
        return this.number;
    }
    /**
     * set user's address
     * 
     * @param user's address
     * @return user's address
     */
    public String setAddress(String address) {
        this.address = address;
        return this.address;
    }

    /**
     * get user's name
     * 
     * @return user's name
     */
    public String getName() {

        return name;
    }

    /**
     * get user's number
     * 
     * @return user's number
     */
    public String getNumber() {

        return number;
    }

    /**
     * get user's address
     * 
     * @return user's address
     */
    public String getAddress() {

        return address;
    }

    /**
     * format string
     * 
     * @return specified format
     */
    public String toString() {
        return String.format("-- %s\r\n-- %s\r\n-- %s", name, number, address);
    }

}
