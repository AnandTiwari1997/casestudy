package com.casestudy.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** This is the main class of this project. From here application will start to run.
 * 
 * @author Anand Tiwari */
@SpringBootApplication
// @EnableScheduling
public class RmsApplication {

    /** Main Method for bootstrapping the application.
     * 
     * @param args
     *            some arguments. */
    public static void main(String[] args) {
        SpringApplication.run(RmsApplication.class, args);
    }
}