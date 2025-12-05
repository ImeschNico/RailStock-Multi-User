package com.railStock.rail_stock.dto;



/**
 * Datenübertragungsobjekt (DTO) für Herstellerinformationen.
 * <p>
 * Dieses Objekt wird verwendet, um Herstellerdaten zwischen Backend und Frontend zu übertragen.
 * Es enthält lediglich die Basisinformationen: eine eindeutige ID und den Namen des Herstellers.
 * </p>
 *
 * <p>
 * Typischerweise in Kombination mit Lokomotiv- oder Produktdaten verwendet,
 * um den Hersteller eindeutig zu identifizieren.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class HerstellerDTO {

    /**
     * Eindeutige ID des Herstellers.
     */
    private final Long id;

    /**
     * Name des Herstellers.
     */
    private final String name;

    /**
     * Konstruktor zur Initialisierung eines {@link HerstellerDTO}.
     *
     * @param id   eindeutige ID des Herstellers
     * @param name Name des Herstellers
     */
    public HerstellerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gibt die eindeutige ID des Herstellers zurück.
     *
     * @return Hersteller-ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt den Namen des Herstellers zurück.
     *
     * @return Herstellername
     */
    public String getName() {
        return name;
    }
}
