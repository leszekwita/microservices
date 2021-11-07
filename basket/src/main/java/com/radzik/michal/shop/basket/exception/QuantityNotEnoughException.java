package com.radzik.michal.shop.basket.exception;

public class QuantityNotEnoughException extends RuntimeException {
    public QuantityNotEnoughException(String message) {
        super(message);
    }
}
