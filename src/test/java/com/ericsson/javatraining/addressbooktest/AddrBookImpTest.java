package com.ericsson.javatraining.addressbooktest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.javatraining.addressbook.AddrBookImp;
import com.ericsson.javatraining.addressbook.ReadAndWrite;

public class AddrBookImpTest {
    String name = "EMA";
    String number = "number";
    String address = "address";
    ReadAndWrite book;
    /**
     * 
     */
    AddrBookImp addrbookimp;
    @Before
    public void setUp() throws Exception {
        book = new ReadAndWrite();
        addrbookimp = new AddrBookImp();
    }


    @Test
    public void testlist() {

        addrbookimp.find(number, book);
        addrbookimp.list(book);
    }

    @Test
    public void testconvert() {
        assertEquals("first letter of return value should be upper case", "Ema", addrbookimp.convert(name));
    }
}
