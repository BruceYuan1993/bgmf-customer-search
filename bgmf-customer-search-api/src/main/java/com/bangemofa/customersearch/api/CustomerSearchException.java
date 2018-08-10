package com.bangemofa.customersearch.api;

/**
 * Created by bruceyuan on 18-8-10.
 */
public class CustomerSearchException extends RuntimeException {
    public CustomerSearchException(String message) {
        super(message);
    }
    public CustomerSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
