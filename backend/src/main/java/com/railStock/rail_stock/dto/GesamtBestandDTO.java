package com.railStock.rail_stock.dto;

/**
 * Datenübertragungsobjekt (DTO) zur Darstellung des gesamten Bestands einer Lokomotive.
 * <p>
 * Dieses Objekt dient dazu, die Gesamtmenge eines bestimmten Artikels (identifiziert durch
 * seine Artikelnummer) über alle Lagerplätze hinweg darzustellen.
 * </p>
 *
 * <p>
 * Typischerweise in {@code GET /api/bestand/gesamt} verwendet, um
 * eine aggregierte Bestandsübersicht zu liefern.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class GesamtBestandDTO{

    /**
     * Artikelnummer der Lokomotive oder des Produkts.
     */
    private String artNumber;

    /**
     * Gesamtmenge dieses Artikels über alle Lagerplätze hinweg.
     */
    private int gesamtMenge;

    /**
     * Konstruktor zur Initialisierung des DTOs mit Artikelnummer und Gesamtmenge.
     *
     * @param artNumber   Artikelnummer des Produkts
     * @param gesamtMenge Gesamtmenge über alle Lagerplätze
     */
    public GesamtBestandDTO(String artNumber, int gesamtMenge) {
        this.artNumber = artNumber;
        this.gesamtMenge = gesamtMenge;

    }

    /**
     * Gibt die Artikelnummer zurück.
     *
     * @return Artikelnummer
     */
    public String getArtNumber() {
        return artNumber;
    }

    /**
     * Setzt die Artikelnummer.
     *
     * @param artNumber neue Artikelnummer
     */
    public void setArtNumber(String artNumber) {
        this.artNumber = artNumber;
    }

    /**
     * Gibt die Gesamtmenge des Artikels zurück.
     *
     * @return Gesamtmenge
     */
    public int getMenge() {
        return gesamtMenge;
    }

    /**
     * Setzt die Gesamtmenge des Artikels.
     *
     * @param menge neue Gesamtmenge
     */
    public void setMenge(int menge) {
        this.gesamtMenge = gesamtMenge;
    }
}
