package com.ericsson.javatraining.implement;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;
import com.ericsson.javatraining.data.ToXML;

/**
 * AddrBookImp implements basic function include add ,find ,list and quit.
 * 
 */
public class AddrBookImp {
    private static final Logger logger = LoggerFactory.getLogger(AddrBookImp.class);
    private Data data;
    private ToXML toxml;
    private Document document;

    public AddrBookImp() {
        super();
        data = Data.getInstance();
        toxml = new ToXML();
    }
    /**
     * Add a new user by user name.
     * 
     * @param name
     *            the user name
     * 
     */
    public void add(String name) {
        PhoneModule phone = new PhoneModule();
        phone.setName(convert(name));
        phone.setNumber(Input.getString("Enter number: "));
        phone.setAddress(Input.getString("Enter address: "));
        data.addPhone(phone);
        data.addtempPhone(phone);
        logger.info("Add a new user");
    }

    /**
     * find existing user by number.
     * 
     * @param number
     *            phone number
     * 
     */
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

    /**
     * List all the user.
     * 
     * no param but only print them
     * 
     */
    public void list() {
        for (PhoneModule phone : data.getPhones()) {
            if (phone != null) {
                System.out.println(phone);
            }
        }
        logger.info("List all the information");
    }

    /**
     * quit this application
     * 
     */
    public void quit() {
        logger.info("Quit the application");
        try {
            document = toxml.createDocumentTree();
            toxml.toxml(document);
        } catch (IOException e) {
            logger.error("failed to store data ", e);
        }
    }

    /**
     * Convert the input name to standard form(lower case).
     * 
     * @param name
     *            the user name
     * 
     */

    public String convert(String name) {

        return name.substring(0).toLowerCase();
    }
}
