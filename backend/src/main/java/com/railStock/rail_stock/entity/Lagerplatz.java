package com.railStock.rail_stock.entity;
import jakarta.persistence.*;

/**
 * Entity-Klasse für Lagerplätze.
 * <p>
 * Diese Klasse bildet die Datenbanktabelle {@code lagerplatz} ab und beschreibt
 * die Position eines Lagerplatzes innerhalb eines Regalsystems.
 * </p>
 *
 * <p>
 * Ein Lagerplatz kann mehreren Beständen zugeordnet sein (1:N-Beziehung zu {@link Bestand}).
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Entity
@Table(name = "lagerplatz")
public class Lagerplatz {

    /**
     * Eindeutige ID des Lagerplatzes.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Bezeichnung des Regals, in dem sich der Lagerplatz befindet.
     */
    @Column(nullable = false, length = 7)
    private String regal;

    /**
     * Bezeichnung des Tablars (Fachs) innerhalb des Regals.
     */
    @Column(nullable = false, length = 7)
    private String tablar;


    //----Konstruktoren ----
    /**
     * Standardkonstruktor für JPA.
     */
    public Lagerplatz() {}

    /**
     * Konstruktor zur Initialisierung eines Lagerplatzes mit Regal und Tablar.
     *
     * @param regal  Regalbezeichnung
     * @param tablar Tablarbezeichnung
     */
    public Lagerplatz(String regal, String tablar) {
        this.regal = regal;
        this.tablar = tablar; }


//----Getter & Setter ----
    /**
     * Gibt die eindeutige ID des Lagerplatzes zurück.
     *
     * @return Lagerplatz-ID
     */
    public Long getId()
    { return id; }

    /**
     * Setzt die eindeutige ID des Lagerplatzes.
     *
     * @param id neue ID
     */
    public void setId(Long id)
    { this.id = id; }

    /**
     * Gibt die Regalbezeichnung zurück.
     *
     * @return Regalbezeichnung
     */
    public String getRegal()
    { return regal; }

    /**
     * Setzt die Regalbezeichnung.
     *
     * @param regal neue Regalbezeichnung
     */
    public void setRegal(String regal)
    { this.regal = regal; }


    /**
     * Gibt die Tablarbezeichnung zurück.
     *
     * @return Tablarbezeichnung
     */
    public String getTablar()
    { return tablar; }

    /**
     * Setzt die Tablarbezeichnung.
     *
     * @param tablar neue Tablarbezeichnung
     */
    public void setTablar(String tablar)
    { this.tablar = tablar; }
}