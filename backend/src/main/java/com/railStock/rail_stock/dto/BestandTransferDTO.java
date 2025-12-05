package com.railStock.rail_stock.dto;



/**
 * Datenübertragungsobjekt (DTO) für den Transfer von Beständen zwischen Lagerplätzen.
 * <p>
 * Dieses Objekt wird verwendet, um eine bestimmte Menge einer Lokomotive
 * von einem Lagerplatz (Regal/Tablar) zu einem anderen zu verschieben.
 * </p>
 *
 * <p>
 * Typischerweise in {@code PUT /api/bestand/transferBestand} eingesetzt.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class BestandTransferDTO {

    /**
     * Artikelnummer der Lokomotive, deren Bestand verschoben wird.
     */
    private String artNumber;

    /**
     * Regalbezeichnung des Ausgangslagerplatzes.
     */
    private String vonRegal;

    /**
     * Tablarbezeichnung (Fach) des Ausgangslagerplatzes.
     */
    private String vonTablar;

    /**
     * Regalbezeichnung des Ziellagerplatzes.
     */
    private String zuRegal;

    /**
     * Tablarbezeichnung (Fach) des Ziellagerplatzes.
     */
    private String zuTablar;

    /**
     * Menge der zu verschiebenden Artikel.
     */
    private int menge;

    /**
     * Konstruktor zur Initialisierung aller Felder.
     *
     * @param artNumber Artikelnummer der Lokomotive
     * @param vonRegal  Ursprungsregal
     * @param vonTablar Ursprungstablar
     * @param zuRegal   Zielregal
     * @param zuTablar  Zieltablar
     * @param menge     Menge der zu verschiebenden Artikel
     */
    public BestandTransferDTO(String artNumber, String vonRegal, String vonTablar, String zuRegal, String zuTablar , int menge) {
        this.artNumber = artNumber;
        this.vonRegal = vonRegal;
        this.vonTablar = vonTablar;
        this.zuRegal = zuRegal;
        this.zuTablar = zuTablar;
        this.menge = menge;

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
     * Gibt das Ursprungsregal zurück.
     *
     * @return Regalbezeichnung
     */
    public String getVonRegal() {
        return vonRegal;
    }

    /**
     * Setzt das Ursprungsregal.
     *
     * @param vonRegal neue Regalbezeichnung
     */
    public void setVonRegal(String vonRegal) {
        this.vonRegal = vonRegal;
    }

    /**
     * Gibt das Ursprungstablar zurück.
     *
     * @return Tablarbezeichnung
     */
    public String getVonTablar() {
        return vonTablar;
    }

    /**
     * Setzt das Ursprungstablar.
     *
     * @param vonTablar neue Tablarbezeichnung
     */
    public void setVonTablar(String vonTablar) {
        this.vonTablar = vonTablar;
    }

    /**
     * Gibt das Zielregal zurück.
     *
     * @return Regalbezeichnung
     */
    public String getZuRegal() {
        return zuRegal;
    }

    /**
     * Setzt das Zielregal.
     *
     * @param zuRegal neue Regalbezeichnung
     */
    public void setZuRegal(String zuRegal) {
        this.zuRegal = zuRegal;
    }

    /**
     * Gibt das Zieltablar zurück.
     *
     * @return Tablarbezeichnung
     */
    public String getZuTablar() {
        return zuTablar;
    }

    /**
     * Setzt das Zieltablar.
     *
     * @param zuTablar neue Tablarbezeichnung
     */
    public void setZuTablar(String zuTablar) {
        this.zuTablar = zuTablar;
    }

    /**
     * Gibt die Menge der zu verschiebenden Artikel zurück.
     *
     * @return Menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Setzt die Menge der zu verschiebenden Artikel.
     *
     * @param menge neue Menge
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }
}
