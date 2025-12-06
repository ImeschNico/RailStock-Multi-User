package com.railStock.rail_stock.exception;

public class DuplicateSerialNumberException extends RuntimeException {

    private final String serialNumber;

    public DuplicateSerialNumberException(String serialNumber) {
        super("Die Seriennummer '" + serialNumber + "' existiert bereits.");
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}