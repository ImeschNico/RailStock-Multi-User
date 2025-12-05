package com.railStock.rail_stock.entity;


import jakarta.persistence.*;

/**
 * Entity-Klasse für den Bestand von Lokomotiven an Lagerplätzen.
 * <p>
 * Diese Klasse bildet die Datenbanktabelle {@code bestand} ab und beschreibt die Zuordnung
 * einer bestimmten Lok zu einem Lagerplatz inklusive der verfügbaren Menge.
 *
 * <p>
 * Beziehungen:
 * <ul>
 *     <li>Viele Bestandseinträge können zu einer Lok gehören (ManyToOne)</li>
 *     <li>Viele Bestandseinträge können zu einem Lagerplatz gehören (ManyToOne)</li>
 * </ul>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Entity
@Table(name = "bestand")
public class Bestand {

    /**
     * Eindeutige ID des Bestands.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Die zugehörige Lokomotive.
     */
    @ManyToOne
    @JoinColumn(name = "lok_id", nullable = false)
    private Lok lok;

    /**
     * Der Lagerplatz, an dem sich die Lokomotive befindet.
     */
    @ManyToOne
    @JoinColumn(name = "lagerplatz_id", nullable = false)
    private Lagerplatz lagerplatz;

    /**
     * Die aktuell verfügbare Menge dieser Lok am angegebenen Lagerplatz.
     */
    @Column(nullable = false)
    private int menge;

    @ManyToOne
    @JoinColumn(name = "hersteller_id")
    private Hersteller hersteller;

    //----Konstruktoren ----
    /**
     * Standardkonstruktor für JPA.
     */
    public Bestand() {}

    /**
     * Konstruktor zur Initialisierung eines Bestands mit Lok, Lagerplatz und Menge.
     *
     * @param lok        zugehörige Lokomotive
     * @param lagerplatz zugehöriger Lagerplatz
     * @param menge      verfügbare Menge
     */
    public Bestand(Lok lok, Lagerplatz lagerplatz, int menge) {
        this.lok = lok;
        this.lagerplatz = lagerplatz;
        this.menge = menge;

    }


    //----Getter & Setter ----

    /**
     * Gibt ID zurück
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Zum Setzen der ID
     *
     * @param id die eindeutige ID des Bestands
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt Lok zurück
     * @return lok
     */
    public Lok getLok() {
        return lok;
    }

    /**
     * Zum Setzen der Lok
     *
     * @param lok die zugehörige Lokomotive
     */
    public void setLok(Lok lok) {
        this.lok = lok;
    }

    /**
     * Gibt Lagerplatz zurück
     * @return lagerplatz
     */
    public Lagerplatz getLagerplatz() {
        return lagerplatz;
    }

    /**
     * Zum Setzen des Lagerplatzes
     *
     * @param lagerplatz der Lagerplatz, an dem sich die Lok befindet
     */
    public void setLagerplatz(Lagerplatz lagerplatz) {
        this.lagerplatz = lagerplatz;
    }

    /**
     * Gibt Mnege zurück
     * @return menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Zum Setzen der Menge
     *
     * @param menge die aktuell verfügbare Menge der Lok am Lagerplatz
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * Gibt hersteller zurück
     * @return hersteller
     */
    public Hersteller getHersteller() {
        return hersteller;
    }

    /**
     * Setzt den Hersteller
     * @param hersteller
     */
    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }
}
