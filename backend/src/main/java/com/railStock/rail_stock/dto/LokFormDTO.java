package com.railStock.rail_stock.dto;

/**
 * Data Transfer Object (DTO) zur Repräsentation der Formulardaten einer Lokomotive.
 * <p>
 * Diese Klasse dient dem Austausch von Lok-Daten zwischen der Benutzeroberfläche
 * und dem Backend. Sie enthält ausschließlich einfache Datentypen und keine Logik.
 * </p>
 * @author Nico Imesch
 * @version 1.0
 */
public class LokFormDTO {
    private String artNumber;
    private String bezeichnung;
    private String typ;
    private String modell;
    private String stromart;
    private String spur;
    private String epoche;
    private String betriebsart;
    private String herstellerName;


    /**
     * Gibt die Artikelnummer der Lokomotive zurück.
     *
     * @return die Artikelnummer
     */
    public String getArtNumber() {
        return artNumber;
    }

    /**
     * Setzt die Artikelnummer der Lokomotive.
     *
     * @param artNumber die Artikelnummer
     */
    public void setArtNumber(String artNumber) {
        this.artNumber = artNumber;
    }

    /**
     * Gibt die Bezeichnung der Lokomotive zurück.
     *
     * @return die Bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Setzt die Bezeichnung der Lokomotive.
     *
     * @param bezeichnung die Bezeichnung
     */
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * Gibt den Typ der Lokomotive zurück.
     *
     * @return der Lok-Typ
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Setzt den Typ der Lokomotive.
     *
     * @param typ der Lok-Typ
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * Gibt das Modell der Lokomotive zurück.
     *
     * @return das Modell
     */
    public String getModell() {
        return modell;
    }

    /**
     * Setzt das Modell der Lokomotive.
     *
     * @param modell das Modell
     */
    public void setModell(String modell) {
        this.modell = modell;
    }

    /**
     * Gibt die Stromart der Lokomotive zurück.
     *
     * @return die Stromart
     */
    public String getStromart() {
        return stromart;
    }

    /**
     * Setzt die Stromart der Lokomotive.
     *
     * @param stromart die Stromart
     */
    public void setStromart(String stromart) {
        this.stromart = stromart;
    }

    /**
     * Gibt die Spurweite der Lokomotive zurück.
     *
     * @return die Spurweite
     */
    public String getSpur() {
        return spur;
    }

    /**
     * Setzt die Spurweite der Lokomotive.
     *
     * @param spur die Spurweite
     */
    public void setSpur(String spur) {
        this.spur = spur;
    }

    /**
     * Gibt die Epoche der Lokomotive zurück.
     *
     * @return die Epoche
     */
    public String getEpoche() {
        return epoche;
    }

    /**
     * Setzt die Epoche der Lokomotive.
     *
     * @param epoche die Epoche
     */
    public void setEpoche(String epoche) {
        this.epoche = epoche;
    }

    /**
     * Gibt die Betriebsart der Lokomotive zurück.
     *
     * @return die Betriebsart
     */
    public String getBetriebsart() {
        return betriebsart;
    }

    /**
     * Setzt die Betriebsart der Lokomotive.
     *
     * @param betriebsart die Betriebsart
     */
    public void setBetriebsart(String betriebsart) {
        this.betriebsart = betriebsart;
    }

    /**
     * Gibt den Herstellernamen der Lokomotive zurück.
     *
     * @return der Herstellernamen
     */
    public String getHerstellerName() {
        return herstellerName;
    }

    /**
     * Setzt den Herstellernamen der Lokomotive.
     *
     * @param herstellerName der Herstellernamen
     */
    public void setHerstellerName(String herstellerName) {
        this.herstellerName = herstellerName;
    }
}
