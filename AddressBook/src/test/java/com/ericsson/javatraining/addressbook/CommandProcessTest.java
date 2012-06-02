package com.ericsson.javatraining.addressbook;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LoggerFactory.class, CommandProcess.class, ReadAndWrite.class })
public class CommandProcessTest {
    private static String addcommand = "add ema";
    private static String listcommand = "list";
    private static String findcommand = "find ema";
    private static String quitcommand = "quit";
    AddrBookImp addrbookimpmock;
    ReadAndWrite readandwritemock;

    @Before
    public void setUp() throws Exception {
        addrbookimpmock = mock(AddrBookImp.class);
        readandwritemock = mock(ReadAndWrite.class);
        whenNew(ReadAndWrite.class).withNoArguments().thenReturn(readandwritemock);
        whenNew(AddrBookImp.class).withNoArguments().thenReturn(addrbookimpmock);
    }

    @Test
    public void testprocess() throws Exception {
        doReturn(addcommand).when(addrbookimpmock).getString("Please input your command: ");
        CommandProcess commandProcess = new CommandProcess();
        commandProcess.process();

        doReturn(listcommand).when(addrbookimpmock).getString("Please input your command: ");
        commandProcess = new CommandProcess();
        commandProcess.process();

        doReturn(findcommand).when(addrbookimpmock).getString("Please input your command: ");
        commandProcess = new CommandProcess();
        commandProcess.process();

        doReturn(quitcommand).when(addrbookimpmock).getString("Please input your command: ");
        commandProcess = new CommandProcess();
        commandProcess.process();

        doReturn("unknown command").when(addrbookimpmock).getString("Please input your command: ");
        commandProcess = new CommandProcess();
        commandProcess.process();
    }

}
