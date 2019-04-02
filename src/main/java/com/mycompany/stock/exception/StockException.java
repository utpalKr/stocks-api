package com.mycompany.stock.exception;

import org.springframework.http.HttpStatus;

public class StockException extends Exception {

    private final HttpStatus status;
    private final String message;

    /**
     * Arg constructor.
     *
     * @param message the {@link String} message to set.
     * @param status the {@link HttpStatus} to set.
     */
    public StockException(final String message, final HttpStatus status) {
        this.status = status;
        this.message = message;
    }

}
