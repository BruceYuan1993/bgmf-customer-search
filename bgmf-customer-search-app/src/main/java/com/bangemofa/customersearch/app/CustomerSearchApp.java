package com.bangemofa.customersearch.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerSearchApp {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSearchApp.class);
    public static void main(String[] args) {
        logger.info("logback 成功了");
        logger.error("logback 成功了");
    }
}
