package com.railStock.rail_stock.dto;


/**
 * Datenübertragungsobjekt (DTO) für Lagerplatzinformationen.
 * <p>
 * Dieses Objekt dient dazu, Lagerplätze zwischen Backend und Frontend zu übertragen.
 * Es enthält die eindeutige ID, die Regalbezeichnung und das Tablar (Fach) des Lagerplatzes.
 * </p>
 *
 * <p>
 * Typischerweise in Kombination mit Bestandsdaten verwendet, z. B. in {@link com.railStock.rail_stock.dto.BestandDTO}.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class LagerplatzDTO {

    /**
     * Eindeutige ID des Lagerplatzes.
     */
    private final Long id;

    /**
     * Bezeichnung des Regals, in dem sich der Lagerplatz befindet.
     */
    private final String regal;

    /**
     * Bezeichnung des Tablars (Fachs) innerhalb des Regals.
     */
    private final String tablar;

    /**
     * Konstruktor zur Initialisierung eines {@link LagerplatzDTO}.
     *
     * @param id    eindeutige ID des Lagerplatzes
     * @param regal Regalbezeichnung
     * @param tablar Tablarbezeichnung
     */
    public LagerplatzDTO(Long id, String regal, String tablar) {
        this.id = id;
        this.regal = regal;
        this.tablar = tablar;
    }

    /**
     * Gibt die Tablarbezeichnung zurück.
     *
     * @return Tablarbezeichnung
     */
    public String getTablar() {
        return tablar;
    }

    /**
     * Gibt die Regalbezeichnung zurück.
     *
     * @return Regalbezeichnung
     */
    public String getRegal() {
        return regal;
    }

    /**
     * Gibt die eindeutige ID des Lagerplatzes zurück.
     *
     * @return Lagerplatz-ID
     */
    public Long getId() {
        return id;
    }
}
