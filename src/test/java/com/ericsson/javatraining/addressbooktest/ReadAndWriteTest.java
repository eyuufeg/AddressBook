package com.ericsson.javatraining.addressbooktest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.addressbook.AddrBookImp;
import com.ericsson.javatraining.addressbook.PhoneModule;
import com.ericsson.javatraining.addressbook.ReadAndWrite;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ReadAndWrite.class, LoggerFactory.class })
public class ReadAndWriteTest {
    final Logger logger = LoggerFactory.getLogger(AddrBookImp.class);
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
        assertEquals(phone, readandwrite.getPhone("ema"));
        assertEquals(phone, readandwrite.gettempPhone("ema"));
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
