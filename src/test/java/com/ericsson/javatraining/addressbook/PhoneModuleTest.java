package com.ericsson.javatraining.addressbook;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.javatraining.addressbook.PhoneModule;

public class PhoneModuleTest {
    PhoneModule phonemodule;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testConstructor() {
        phonemodule = new PhoneModule("name::number::address");
        assertEquals("field name", "name", phonemodule.getName());
        assertEquals("field number", "number", phonemodule.getNumber());
        assertEquals("field address", "address", phonemodule.getAddress());
    }

    @Test
    public void testSetAndGet() {
        phonemodule = new PhoneModule();
        phonemodule.setAddress("address");
        phonemodule.setName("name");
        phonemodule.setNumber("number");
        assertEquals("function getName", "name", phonemodule.getName());
        assertEquals("function getNumber", "number", phonemodule.getNumber());
        assertEquals("function getAddress", "address", phonemodule.getAddress());
    }
}
