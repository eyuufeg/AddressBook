package com.ericsson.javatraining.addressbook;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddrBookImp {
    final Logger logger = LoggerFactory.getLogger(AddrBookImp.class);
    public AddrBookImp() {
        System.out.println("################ADDRESS BOOK APPLICATION STARTING################");
        System.out.println("######################USAGE OF THE COMMANDS######################");
        System.out.println("add <name>");
        System.out.println("find <number>");
        System.out.println("list");
        System.out.println("quit");
        System.out.println("#################################################################");
    }

    public void add(String name, ReadAndWrite book) {
        PhoneModule phone = new PhoneModule();
        phone.name = convert(name);
        phone.number = getString("Enter number: ");
        phone.address = getString("Enter address: ");
        book.addPhone(phone);
        book.addtempPhone(phone);
        logger.info("Add a new user");
    }

    public void find(String number, ReadAndWrite book) {

        for (PhoneModule phone : book.getPhones()) {
            if (phone != null) {
                if (phone.number.indexOf(number) < 0) {
                    continue;
                } else {
                    logger.info("Find the user with number: " + number);
                    System.out.println(phone);
            }
            }
        }

    }

    public void list(ReadAndWrite book) {
        for (PhoneModule phone : book.getPhones()) {
            if (phone != null) {

                System.out.println(phone);
            }
        }
        logger.info("List all the information");
    }

    public void quit(ReadAndWrite book) {
        try {
            book.store();
        } catch (IOException e) {
        }
        logger.info("Quit the application");
        System.exit(0);
    }

    public String getString(String tip) {
        System.out.print(tip);

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String convert(String name) {
        if (name != null && name.length() > 0) {
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return null;
    }
}
