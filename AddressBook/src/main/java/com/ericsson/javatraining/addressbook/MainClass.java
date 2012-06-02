package com.ericsson.javatraining.addressbook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MainClass {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(MainClass.class);
        logger.info(" AddressBook application starting");

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {

            public void run() {
                final CommandProcess cmdprocessing = new CommandProcess();
                while (true) {
                    cmdprocessing.process();
                }

            }
        });
        service.shutdown();
    }
}