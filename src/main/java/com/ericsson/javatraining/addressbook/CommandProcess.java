package com.ericsson.javatraining.addressbook;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.implement.AddrBookImp;
import com.ericsson.javatraining.xml.ReadAndWrite;

public class CommandProcess {

    private static final Logger logger = LoggerFactory.getLogger(ReadAndWrite.class);
    private ReadAndWrite readandwrite = new ReadAndWrite();
    private AddrBookImp addrbookimpl = new AddrBookImp();

    public void process() {
        while (true) {
            String cmd = addrbookimpl.getString("Please input your command: ");
            if (cmd.startsWith("add "))
                addrbookimpl.add(cmd.substring(cmd.indexOf(' ') + 1));
            else if (cmd.startsWith("find "))
                addrbookimpl.find(cmd.substring(cmd.indexOf(' ') + 1));
            else if (cmd.equals("list"))
                addrbookimpl.list();
            else if (cmd.equals("quit")) {
                addrbookimpl.quit();
                try {
                    readandwrite.store();
                } catch (IOException e) {
                    logger.error("failed to store data ", e);
                }
                break;
            } else
                System.out.println("unknown command!");
        }
    }



}
