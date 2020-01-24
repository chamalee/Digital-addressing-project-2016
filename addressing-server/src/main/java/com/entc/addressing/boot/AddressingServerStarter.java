package com.entc.addressing.boot;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressingServerStarter {

    public static void main(String[] args) throws Exception {
        final ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("addressing-context.xml");
        context.registerShutdownHook();

        logStartupMessage();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logStoppedMessage();
            }
        });

        context.start();
        Thread.currentThread().join();
    }

    private static void logStartupMessage() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("|                 DIGITAL ADDRESSING SERVER STARTED!                   |");
        System.out.println("------------------------------------------------------------------------");
    }

    private static void logStoppedMessage() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("|                 DIGITAL ADDRESSING SERVER STOPPED!                   |");
        System.out.println("------------------------------------------------------------------------");
    }
}
