package com.railStock.rail_stock.entity;

import jakarta.persistence.*;

/**
 * Entity-Klasse für Herstellerinformationen.
 * <p>
 * Diese Klasse bildet die Datenbanktabelle {@code hersteller} ab und speichert
 * die Basisinformationen eines Herstellers, wie ID und Name.
 * </p>
 *
 * <p>
 * Typischerweise wird ein Hersteller mit einer oder mehreren Lokomotiven assoziiert.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Entity
@Table(name = "hersteller")
public class Hersteller {

    /**
     * Eindeutige ID des Herstellers.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name des Herstellers.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Standardkonstruktor für JPA.
     */
    public Hersteller() {}

    /**
     * Konstruktor zur Initialisierung eines Herstellers mit Name.
     *
     * @param name Name des Herstellers
     */
    public Hersteller(String name) {
        this.name = name;
    }


    //---Getter und Setter ----
    /**
     * Gibt die eindeutige ID des Herstellers zurück.
     *
     * @return Hersteller-ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die eindeutige ID des Herstellers.
     *
     * @param id neue ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt den Namen des Herstellers zurück.
     *
     * @return Herstellername
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Herstellers.
     *
     * @param name neuer Name
     */
    public void setName(String name) {
        this.name = name;
    }
}
