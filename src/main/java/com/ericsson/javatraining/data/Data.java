package com.ericsson.javatraining.data;

/**
 * Data used to storage global phones data. It only can be initialized once
 * 
 * @author eyuufeg
 */
public class Data {
    private static Data instance = null;
    private int size;
    private int tempsize;
    private PhoneModule[] phones;
    private PhoneModule[] tempphones;

    public Data() {
        phones = new PhoneModule[100];
        tempphones = new PhoneModule[100];
    }

    /**
     * 
     * @return instance
     * 
     */
    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public void addPhone(PhoneModule phone) {
        phones[size++] = phone;
    }

    /**
     * tempphones used to save the new added info
     */
    public void addtempPhone(PhoneModule phone) {
        tempphones[tempsize++] = phone;
    }

    public PhoneModule getPhone(String number) {
        for (PhoneModule phone : phones) {
            if (phone == null) {
                continue;
            }
            if (phone.getNumber().equalsIgnoreCase(number)) {
                return phone;
            }
        }
        return null;
    }

    public PhoneModule[] getPhones() {
        return phones;
    }

    public PhoneModule[] gettempPhones() {
        return tempphones;
    }
}
