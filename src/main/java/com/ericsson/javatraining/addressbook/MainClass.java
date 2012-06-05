package com.ericsson.javatraining.addressbook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eyuufeg
 * 
 * 
 */
public class MainClass {
    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        logger.info(" AddressBook application starting");
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            public void run() {
                final CommandProcess cmdprocessing = new CommandProcess();
                    cmdprocessing.process();
            }
        });
        service.shutdownNow();
    }
}