package com.railStock.rail_stock.dto;


import com.railStock.rail_stock.entity.Lagerplatz;
import com.railStock.rail_stock.entity.Lok;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Datenübertragungsobjekt (DTO) für Bestandsinformationen.
 * <p>
 * Dieses DTO repräsentiert die Zuordnung zwischen einer {@link Lok}
 * und einem {@link Lagerplatz} inklusive der aktuellen Menge.
 * Es dient der Entkopplung zwischen Datenbank-Entitäten und den REST-API-Responses.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class BestandDTO {

    /**
     * Eindeutige ID des Bestandsdatensatzes.
     */
    private Long id;

    /**
     * Die zugehörige Lokomotive.
     */
    private LokDTO lok;

    /**
     * Der Lagerplatz, an dem sich die Lokomotive befindet.
     */
    private LagerplatzDTO lagerplatz;

    /**
     * Die aktuell verfügbare Menge dieser Lokomotive am angegebenen Lagerplatz.
     */
    private int menge;

    private String herstellerName;

    /**
     * Konstruktor zur Initialisierung eines {@link BestandDTO}-Objekts.
     *
     * @param id          eindeutige ID des Bestands
     * @param lok         zugehörige Lokomotive
     * @param lagerplatz  zugehöriger Lagerplatz
     * @param menge       verfügbare Menge
     */
    public BestandDTO(Long id, LokDTO lok, LagerplatzDTO lagerplatz, int menge, String herstellerName) {
        this.id = id;
        this.lok = lok;
        this.lagerplatz = lagerplatz;
        this.menge = menge;
        this.herstellerName = herstellerName;
    }

    /**
     * Gibt die ID des Bestands zurück.
     *
     * @return ID des Bestands
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die ID des Bestands.
     *
     * @param id neue ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt die zugehörige Lokomotive zurück.
     *
     * @return {@link LokDTO} der Lokomotive
     */
    public LokDTO getLok() {
        return lok;
    }

    /**
     * Setzt die Lokomotive für diesen Bestand.
     *
     * @param lok neue {@link LokDTO}
     */
    public void setLok(LokDTO lok) {
        this.lok = lok;
    }

    /**
     * Gibt den zugehörigen Lagerplatz zurück.
     *
     * @return {@link LagerplatzDTO} des Lagerplatzes
     */
    public LagerplatzDTO getLagerplatz() {
        return lagerplatz;
    }

    /**
     * Setzt den Lagerplatz für diesen Bestand.
     *
     * @param lagerplatz neuer {@link LagerplatzDTO}
     */
    public void setLagerplatz(LagerplatzDTO lagerplatz) {
        this.lagerplatz = lagerplatz;
    }

    /**
     * Gibt die verfügbare Menge dieses Bestands zurück.
     *
     * @return aktuelle Menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Setzt die verfügbare Menge dieses Bestands.
     *
     * @param menge neue Menge
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    public String getHerstellerName() {
        return herstellerName;
    }
}
