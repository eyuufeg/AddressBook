package com.ericsson.javatraining.implement;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ CommandProcessTest.class, Input.class })
public class CommandProcessTest {
    private CommandProcess commandprocess;

    @Before
    public void setUp() throws Exception {
        commandprocess = new CommandProcess();
        mockStatic(Input.class);
        when(Input.getString("Please input your command: ")).thenReturn("quit");
    }

    @Test
    public void test() {
        commandprocess.process();
        verifyStatic();
        Input.getString("Please input your command: ");
    }

}
