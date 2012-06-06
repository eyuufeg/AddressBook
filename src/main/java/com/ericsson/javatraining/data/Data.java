package com.ericsson.javatraining.data;


public class Data {
    private int size = 0;
    private int tempsize = 0;
    private PhoneModule[] phones = new PhoneModule[100];
    private PhoneModule[] tempphones = new PhoneModule[100];
    private static Data instance = null;

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
     * tempphones used to save the new add info
     */
    public void addtempPhone(PhoneModule phone) {
        tempphones[tempsize++] = phone;
    }

    public PhoneModule getPhone(String name) {
        for (PhoneModule phone : phones) {
            if (phone == null) {
                continue;
            }
            if (phone.getName().equalsIgnoreCase(name)) {
                return phone;
            }
        }
        return null;
    }

    public PhoneModule gettempPhone(String name) {
        for (PhoneModule phone : tempphones) {
            if (phone == null) {
                continue;
            }
            if (phone.getName().equalsIgnoreCase(name)) {
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
