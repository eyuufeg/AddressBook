package com.ericsson.javatraining.implement;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.data.FromXML;

public class CommandProcess {

    private static final Logger logger = LoggerFactory.getLogger(CommandProcess.class);
    private AddrBookImp addrbookimpl = new AddrBookImp();
    private FromXML fromxml = new FromXML();

    public CommandProcess() {
        super();
        System.out.println("****************ADDRESS BOOK APPLICATION STARTING****************");
        System.out.println("****************USAGE OF THE COMMANDS            ****************");
        System.out.println("                add <name>                                       ");
        System.out.println("                find <number>                                    ");
        System.out.println("                list                                             ");
        System.out.println("                quit                                             ");
        System.out.println("*****************************************************************");
        try {
            fromxml.read();
        } catch (IOException e) {
            logger.error("failed to store data ", e);
        }

    }
    /**
     * This method process command received from screen with no param
     * 
     * 
     */
    public void process(String cmd) {

            if (cmd.startsWith("add "))
                addrbookimpl.add(cmd.substring(cmd.indexOf(' ') + 1));
            else if (cmd.startsWith("find "))
                addrbookimpl.find(cmd.substring(cmd.indexOf(' ') + 1));
            else if (cmd.equals("list"))
                addrbookimpl.list();
        else
                System.out.println("unknown command!");
        }

}
