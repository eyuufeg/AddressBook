package com.ericsson.javatraining.implement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;
import com.ericsson.javatraining.exception.AddressBookException;
import com.ericsson.javatraining.xml.FromXML;
import com.ericsson.javatraining.xml.ToXML;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AddrBookImpTest.class, Input.class, AddrBookImp.class, LoggerFactory.class })
public class AddrBookImpTest {
    private static final Logger mocLogger = mock(Logger.class);
    private static String FILENAME = "phonebook.xml";
    private String expectedName = "ema";
    private String expectedNumber = "123";
    private String expectedAddress = "shanghai";
    private AddrBookImp addrbookimp;
    private PhoneModule phonemodule;
    private Data data;
    private FromXML fromxml = new FromXML();

    @Before
    public void setUp() throws Exception {
        mockStatic(LoggerFactory.class);
        when(LoggerFactory.getLogger(AddrBookImp.class)).thenReturn(mocLogger);
        when(LoggerFactory.getLogger(ToXML.class)).thenReturn(mocLogger);
        when(mocLogger.isInfoEnabled()).thenReturn(true);
        when(mocLogger.isDebugEnabled()).thenReturn(true);
        data = Data.getInstance();
        mockStatic(Input.class);

        when(Input.getString("Enter number: ")).thenReturn(expectedNumber);
        when(Input.getString("Enter address: ")).thenReturn(expectedAddress);

        addrbookimp = new AddrBookImp();
        phonemodule = new PhoneModule();
    }

    @After
    public void tearDown() throws Exception {
        File xmlFile = new File(FILENAME);
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
    }

    @Test
    public void testadd() {
        phonemodule.setName(expectedName);
        phonemodule.setNumber(expectedNumber);
        phonemodule.setAddress(expectedAddress);
        addrbookimp.add(expectedName);

        assertEquals("global data should contain name info", phonemodule.getName(), data.getPhone(expectedNumber).getName());
        assertEquals("global data should contain number info", phonemodule.getNumber(), data.getPhone(expectedNumber).getNumber());
        assertEquals("global data should contain address info", phonemodule.getAddress(), data.getPhone(expectedNumber).getAddress());
        verify(mocLogger).info("Add a new user");
    }

    @Test
    public void testconvert() {
        assertEquals("return value should be lower case", "ema", addrbookimp.convert(expectedName));
        assertEquals("return value should be null when input is null", "", addrbookimp.convert(""));
    }

    @Test
    public void testfind() {
        addrbookimp.find(expectedNumber);
        verify(mocLogger).info("Find the user include number: " + expectedNumber);
    }

    @Test
    public void testlist() {
        addrbookimp.list();
        verify(mocLogger).info("List all the information");
    }

    @Test
    public void testquit() {
        try {
            fromxml.read();
        } catch (AddressBookException e) {
        }
        addrbookimp.quit();
        verify(mocLogger).info("Quit the application");
    }
}
