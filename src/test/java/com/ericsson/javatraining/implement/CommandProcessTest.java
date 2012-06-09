package com.ericsson.javatraining.implement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ CommandProcessTest.class, CommandProcess.class, AddrBookImp.class })
public class CommandProcessTest {
    private static String FILENAME = "phonebook.xml";
    private AddrBookImp addrbookimpmock;
    private CommandProcess commandprocess;

    @Before
    public void setUp() throws Exception {
        addrbookimpmock = mock(AddrBookImp.class);
        whenNew(AddrBookImp.class).withNoArguments().thenReturn(addrbookimpmock);
        commandprocess = new CommandProcess();
    }

    @After
    public void tearDown() throws Exception {
        File xmlFile = new File(FILENAME);
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
    }

    @Test
    public void testCommandprocessadd() {
        commandprocess.process("add ema");
        verify(addrbookimpmock).add("ema");

    }

    @Test
    public void testCommandprocessfind() {
        commandprocess.process("find 123");
        verify(addrbookimpmock).find("123");
    }

    @Test
    public void testCommandprocessquickfind() {
        commandprocess.process("quickfind 123");
        verify(addrbookimpmock).quickfind("123");
    }

    @Test
    public void testCommandprocesslist() {
        commandprocess.process("list");
        verify(addrbookimpmock).list();
    }


}
