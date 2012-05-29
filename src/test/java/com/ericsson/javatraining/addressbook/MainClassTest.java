package com.ericsson.javatraining.addressbook;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LoggerFactory.class, CommandProcess.class, ReadAndWrite.class })
public class MainClassTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testmain() throws Exception {
        String[] args = new String[1];
        MainClass.main(args);

    }

}
