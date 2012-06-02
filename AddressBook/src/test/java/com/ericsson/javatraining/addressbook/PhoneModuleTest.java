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
        assertEquals("name", phonemodule.name);
        assertEquals("number", phonemodule.number);
        assertEquals("address", phonemodule.address);
    }

    @Test
    public void testSetAndGet() {
        phonemodule = new PhoneModule();
        phonemodule.setAddress("address");
        phonemodule.setName("name");
        phonemodule.setNumber("number");
        assertEquals("name", phonemodule.getName());
        assertEquals("number", phonemodule.getNumber());
        assertEquals("address", phonemodule.getAddress());
    }
}
