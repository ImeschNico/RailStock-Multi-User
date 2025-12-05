package com.railStock.rail_stock.dto;


/**
 * Datenübertragungsobjekt (DTO) für die Aktualisierung oder Erfassung eines Bestands.
 * <p>
 * Dieses Objekt wird typischerweise in HTTP-Requests verwendet, um Bestandsänderungen
 * vom Frontend an das Backend zu übermitteln. Es enthält die grundlegenden Informationen
 * zu einer Lokomotive und deren Lagerplatz sowie die gewünschte Menge.
 * </p>
 *
 * <p>
 * Wird z. B. in {@code PUT /api/bestand/updateBestand} verwendet.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class BestandFormDTO {

    /**
     * Artikelnummer der Lokomotive, deren Bestand verändert werden soll.
     */

    private String artNumber;

    /**
     * Bezeichnung des Regals, in dem sich der Bestand befindet oder befinden soll.
     */
    private String regal;

    /**
     * Bezeichnung des Tablars (Fachs) innerhalb des Regals.
     */
    private String tablar;

    /**
     * Menge, die im Bestand gesetzt oder angepasst werden soll.
     */
    private int menge;

    /**
     * Standardkonstruktor, der leere oder Nullwerte initialisiert.
     */
    public BestandFormDTO() {
        this.artNumber = "";
        this.regal = "";
        this.tablar = "";
        this.menge = 0;

    }

    /**
     * Gibt die Artikelnummer der Lok zurück.
     *
     * @return Artikelnummer
     */
    public String getArtNumber() {
        return artNumber;
    }

    /**
     * Setzt die Artikelnummer der Lok.
     *
     * @param artNumber neue Artikelnummer
     */
    public void setArtNumber(String artNumber) {
        this.artNumber = artNumber;
    }

    /**
     * Gibt das Regal zurück, in dem sich der Bestand befindet.
     *
     * @return Regalbezeichnung
     */
    public String getRegal() {
        return regal;
    }

    /**
     * Setzt das Regal für den Bestand.
     *
     * @param regal neue Regalbezeichnung
     */
    public void setRegal(String regal) {
        this.regal = regal;
    }

    /**
     * Gibt das Tablar (Fach) zurück, in dem sich der Bestand befindet.
     *
     * @return Tablarbezeichnung
     */
    public String getTablar() {
        return tablar;
    }

    /**
     * Setzt das Tablar (Fach) für den Bestand.
     *
     * @param tablar neue Tablarbezeichnung
     */
    public void setTablar(String tablar) {
        this.tablar = tablar;
    }

    /**
     * Gibt die aktuelle Menge im Bestand zurück.
     *
     * @return aktuelle Menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Setzt die Menge im Bestand.
     *
     * @param menge neue Menge
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }
}
