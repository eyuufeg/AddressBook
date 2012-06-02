package com.ericsson.javatraining.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ReadAndWrite.class, LoggerFactory.class })
public class ReadAndWriteTest {
    ReadAndWrite readandwritemock;
    private PhoneModule[] phones = new PhoneModule[100];
    private PhoneModule[] tempphones = new PhoneModule[100];
    private List<String> recordlist = new ArrayList<String>();
    private String FILENAME = "phonebook.xml";
    private Document document;
    private Element root;
    @Before
    public void setUp() throws Exception {

        // readandwritemock = mock(ReadAndWrite.class);
        // whenNew(ReadAndWrite.class).withNoArguments().thenReturn(readandwritemock);
    }

    @Test
    public void testAddAndGetphone() {

        readandwritemock = new ReadAndWrite();
        PhoneModule phone = new PhoneModule("ema::ema::ema");
        // phones[0]=phone;
        // System.out.println(phone);
        readandwritemock.addPhone(phone);
        readandwritemock.addtempPhone(phone);
        assertEquals(phone, readandwritemock.getPhone("ema"));
        assertEquals(phone, readandwritemock.gettempPhone("ema"));
        // assertEquals(phones, readandwritemock.getPhones());
    }

    @Test
    public void teststore() {

        readandwritemock = new ReadAndWrite();
        try {
            readandwritemock.store();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
