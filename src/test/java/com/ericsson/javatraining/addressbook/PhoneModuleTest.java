package com.ericsson.javatraining.addressbook;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ PhoneModuleTest.class, LoggerFactory.class })
public class PhoneModuleTest {
    PhoneModule phonemodule;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testConstructor() {
        phonemodule = new PhoneModule("name::number::address");
        assertEquals("field name", "name", phonemodule.name);
        assertEquals("field number", "number", phonemodule.number);
        assertEquals("field address", "address", phonemodule.address);
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
