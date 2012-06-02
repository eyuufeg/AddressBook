package com.ericsson.javatraining.addressbook;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AddrBookImp.class, ReadAndWrite.class, LoggerFactory.class })
public class AddrBookImpTest {
    String name = "EMA";
    String number = "number";
    String address = "address";
    ReadAndWrite readandwritemock;
    /**
     * 
     */
    AddrBookImp addrbookimpmock;
    AddrBookImp addrbookimp;
    @Before
    public void setUp() throws Exception {

        addrbookimp = new AddrBookImp();
    }


    @Test
    public void testlist() {

        ReadAndWrite book = new ReadAndWrite();
        addrbookimp.find(number, book);
        addrbookimp.list(book);

    }

    @Test
    public void testconvert() {
        assertEquals("Ema", addrbookimp.convert(name));
    }
}
