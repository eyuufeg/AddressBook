package com.ericsson.javatraining.addressbook;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.ericsson.javatraining.implement.AddrBookImp;
import com.ericsson.javatraining.xml.fromXML;
import com.ericsson.javatraining.xml.toXML;

public class CommandProcess {

    private static final Logger logger = LoggerFactory.getLogger(CommandProcess.class);
    private toXML toxml = new toXML();
    private AddrBookImp addrbookimpl = new AddrBookImp();
    private fromXML fromxml = new fromXML();
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
                    Document document = toxml.createDocumentTree();
                    toxml.toxml(document);
                } catch (IOException e) {
                    logger.error("failed to store data ", e);
                }
                break;
            } else
                System.out.println("unknown command!");
        }
    }



}
