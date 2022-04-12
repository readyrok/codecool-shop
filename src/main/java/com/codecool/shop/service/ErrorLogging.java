package com.codecool.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public class ErrorLogging {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLogging.class);

    public static void log(Exception e) {
        if (e instanceof IOException) {
            logger.error("File not found! {}.", e.getMessage());
        } else if (e instanceof NullPointerException) {
            logger.warn("Can not load properties from file! {}", e.getMessage());
        } else if (e instanceof SQLException) {
            logger.error("Cannot reach database! {}", e.getMessage());
        } else if (e instanceof MessagingException) {
            logger.warn("Couldn't send the e-mail! {}", e.getMessage());
        } else if (e instanceof IllegalAccessException) {
            logger.warn("Error {}", e.getMessage());
        }
    }
}