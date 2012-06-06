package com.ericsson.javatraining.implement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AddrBookImpTest.class, Input.class })
public class AddrBookImpTest {
    private String expectedName = "zhangsan";
    private String expectedNumber = "123";
    private String expectedAddress = "shanghai";
    private AddrBookImp addrbookimp;
    private PhoneModule phonemodule;
    private Data data;
    @Before
    public void setUp() throws Exception {
        addrbookimp = new AddrBookImp();
        phonemodule = new PhoneModule();
        data = Data.getInstance();
        mockStatic(Input.class);
        when(Input.getString("Enter number: ")).thenReturn(expectedNumber);
        when(Input.getString("Enter address: ")).thenReturn(expectedAddress);
    }

    @Test
    public void testadd() {
        phonemodule.setName(expectedName);
        phonemodule.setNumber(expectedNumber);
        phonemodule.setAddress(expectedAddress);
        addrbookimp.add(expectedName);
        assertEquals("global data should contain name info of zhangsan", phonemodule.getName(), data.getPhone(expectedName).getName());
        assertEquals("global data should contain number info of zhangsan", phonemodule.getNumber(), data.getPhone(expectedName).getNumber());
        assertEquals("global data should contain address info of zhangsan", phonemodule.getAddress(), data.getPhone(expectedName).getAddress());

    }

    @Test
    public void testconvert() {
        assertEquals("return value should be lower case", "zhangsan", addrbookimp.convert(expectedName));
        assertEquals("return value should be null when input is null", "", addrbookimp.convert(""));
    }
}
