package com.ericsson.javatraining.xml;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ReadAndWriteTest {

    ReadAndWrite readandwrite;
    private Data data = Data.getInstance();
    @Before
    public void setUp() throws Exception {
        readandwrite = new ReadAndWrite();
    }

    @Test
    public void testAddAndGetphone() {

        PhoneModule phone = new PhoneModule("ema::123::shanghai");
        data.addPhone(phone);
        data.addtempPhone(phone);
        assertEquals("it should return phone", phone, data.getPhone("ema"));
        assertEquals("return phone", phone, data.gettempPhone("ema"));
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
