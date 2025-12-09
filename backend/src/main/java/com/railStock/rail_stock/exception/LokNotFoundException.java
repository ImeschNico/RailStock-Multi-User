package com.railStock.rail_stock.exception;

public class LokNotFoundException extends RuntimeException {

    private final String artNumber;

    public LokNotFoundException(String artNumber) {
        super("Lok mit ID " + artNumber + " wurde nicht gefunden.");
        this.artNumber = artNumber;
    }

    public String getArtNumber() {
        return artNumber;
    }
}