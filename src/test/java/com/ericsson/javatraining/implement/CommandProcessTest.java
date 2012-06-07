package com.ericsson.javatraining.implement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ericsson.javatraining.data.Data;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ CommandProcessTest.class, Input.class })
public class CommandProcessTest {
    private CommandProcess commandprocess;
    private Data data;
    @Before
    public void setUp() throws Exception {
        commandprocess = new CommandProcess();
        data = Data.getInstance();
        mockStatic(Input.class);
        when(Input.getString("Enter number: ")).thenReturn("123");
        when(Input.getString("Enter address: ")).thenReturn("shanghai");
    }

    @Test
    public void testCommandprocess() {
        commandprocess.process("add ema");
        commandprocess.process("find 123");
        commandprocess.process("list");
        commandprocess.process("quit");
        verifyStatic();
        Input.getString("Enter number: ");
        assertEquals("return value should be 123", "123", data.getPhone("ema").getNumber());
        assertEquals("return value should be shanghai", "shanghai", data.getPhone("ema").getAddress());
    }

}
