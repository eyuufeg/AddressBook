package com.ericsson.javatraining.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ReadAndWrite.class, LoggerFactory.class })
public class ReadAndWriteTest {
    final Logger logger = LoggerFactory.getLogger(AddrBookImp.class);
    ReadAndWrite readandwritemock;
    @Before
    public void setUp() throws Exception {

        // readandwritemock = mock(ReadAndWrite.class);
        // whenNew(ReadAndWrite.class).withNoArguments().thenReturn(readandwritemock);
    }

    @Test
    public void testAddAndGetphone() {
        readandwritemock = new ReadAndWrite();
        PhoneModule phone = new PhoneModule("ema::ema::ema");
        readandwritemock.addPhone(phone);
        readandwritemock.addtempPhone(phone);
        assertEquals(phone, readandwritemock.getPhone("ema"));
        assertEquals(phone, readandwritemock.gettempPhone("ema"));
    }

    @Test
    public void teststore() {

        readandwritemock = new ReadAndWrite();
        try {
            readandwritemock.store();
        } catch (IOException e) {
            System.out.println("IOException");
        }


    }

}
