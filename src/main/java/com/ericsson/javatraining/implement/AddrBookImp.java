package com.ericsson.javatraining.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;

public class AddrBookImp {
    private static final Logger logger = LoggerFactory.getLogger(AddrBookImp.class);
    private Data data = Data.getInstance();

    public AddrBookImp() {
        System.out.println("****************ADDRESS BOOK APPLICATION STARTING****************");
        System.out.println("****************USAGE OF THE COMMANDS            ****************");
        System.out.println("                add <name>                                       ");
        System.out.println("                find <number>                                    ");
        System.out.println("                list                                             ");
        System.out.println("                quit                                             ");
        System.out.println("*****************************************************************");
    }

    public void add(String name) {
        PhoneModule phone = new PhoneModule();
        phone.setName(convert(name));
        phone.setNumber(Input.getString("Enter number: "));
        phone.setAddress(Input.getString("Enter address: "));
        data.addPhone(phone);
        data.addtempPhone(phone);
        logger.info("Add a new user");
    }

    public void find(String number) {

        for (PhoneModule phone : data.getPhones()) {
            if (phone != null) {
                if (phone.getNumber().indexOf(number) < 0) {
                    continue;
                } else {
                    logger.info("Find the user with number: " + number);
                    System.out.println(phone);
            }
            }
        }

    }

    public void list() {
        for (PhoneModule phone : data.getPhones()) {
            if (phone != null) {
                System.out.println(phone);
            }
        }
        logger.info("List all the information");
    }

    public void quit() {
        logger.info("Quit the application");
        return;
    }



    public String convert(String name) {

        return name.substring(0).toLowerCase();
    }
}
