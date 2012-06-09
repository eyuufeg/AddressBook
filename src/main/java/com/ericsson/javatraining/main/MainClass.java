package com.ericsson.javatraining.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.javatraining.implement.AddrBookImp;
import com.ericsson.javatraining.implement.CommandProcess;
import com.ericsson.javatraining.implement.Input;

/**
 * main class of AddressBook program
 * 
 * @author eyuufeg
 * 
 */
public class MainClass {
    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        logger.info(" AddressBook application starting");
        final AddrBookImp addrbookimpl = new AddrBookImp();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            public void run() {
                final CommandProcess cmdprocessing = new CommandProcess();
                while (true) {
                    String cmd = Input.getString("Please input your command: ");
                    if (cmd.equals("quit")) {
                        addrbookimpl.quit();
                        logger.info("Store all the information");
                        break;
                    } else
                        cmdprocessing.process(cmd);
                }
            }
        });
        service.shutdownNow();
    }

}