package com.ericsson.javatraining.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ReadAndWriteTest {

    ReadAndWrite readandwrite;
    @Before
    public void setUp() throws Exception {
        readandwrite = new ReadAndWrite();
    }

    @Test
    public void testAddAndGetphone() {

        PhoneModule phone = new PhoneModule("ema::123::shanghai");
        readandwrite.addPhone(phone);
        readandwrite.addtempPhone(phone);
        assertEquals("it should return phone", phone, readandwrite.getPhone("ema"));
        assertEquals("return phone", phone, readandwrite.gettempPhone("ema"));
    }

    @Test
    public void teststore() {
        try {
            readandwrite.store();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Test
    public void testread() {
        try {
            readandwrite.read();
        } catch (IOException e) {
            System.out.println("IOException");
        }


    }
}
