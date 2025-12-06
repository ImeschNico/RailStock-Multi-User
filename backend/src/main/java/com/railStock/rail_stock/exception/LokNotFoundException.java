package com.railStock.rail_stock.exception;

public class LokNotFoundException extends RuntimeException {

    private final Long lokId;

    public LokNotFoundException(Long lokId) {
        super("Lok mit ID " + lokId + " wurde nicht gefunden.");
        this.lokId = lokId;
    }

    public Long getLokId() {
        return lokId;
    }
}