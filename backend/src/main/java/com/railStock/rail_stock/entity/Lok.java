package com.railStock.rail_stock.entity;


import jakarta.persistence.*;

/**
 * Entity-Klasse für Lokomotiven.
 * <p>
 * Diese Klasse bildet die Datenbanktabelle {@code lok} ab und speichert alle relevanten
 * Informationen zu einer Lokomotive, einschließlich Typ, Modell, Stromart, Spur, Epoche,
 * Betriebsart und Hersteller.
 *
 * <p>
 * Beziehung:
 * <ul>
 *     <li>Viele Lok-Einträge können zu einem Hersteller gehören (ManyToOne)</li>
 * </ul>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Entity
@Table(name = "lok")
public class Lok {

    /**
     * Eindeutige ID der Lok.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Artikelnummer der Lokomotive.
     */
    @Column(nullable = false, length = 50)
    private String artNumber;

    /**
     * Bezeichnung der Lok.
     */
    @Column(nullable = false, length = 1000)
    private String bezeichnung;


    /**
     * Typ der Lok (z. B. E-Lok, Diesellok).
     */
    @Column(nullable = false, length = 100)
    private String typ;

    /**
     * Modell oder Baureihe der Lok.
     */
    @Column(nullable = false, length = 100)
    private String modell;

    /**
     * Stromart (z. B. AC, DC, Digital).
     */
    @Column(nullable = false, length = 30)
    private String stromart;

    /**
     * Spurweite (z. B. H0, N).
     */
    @Column(nullable = false, length = 10)
    private String spur;

    /**
     * Epoche der Lok (z. B. III, IV).
     */
    @Column(nullable = false, length = 10)
    private String epoche;

    /**
     * Betriebsart (z. B. Analog, Digital).
     */
    @Column(nullable = false, length = 20)
    private String betriebsart;


    //----Beziehung zu Hersteller ----
    /**
     * Hersteller der Lokomotive.
     */
    @ManyToOne
    @JoinColumn(name = "hersteller_id", nullable = false)
    private Hersteller hersteller;


    //----Konstruktoren----
    /**
     * Standardkonstruktor für JPA.
     */
    public Lok() {}

    /**
     * Konstruktor zur Initialisierung einer Lokomotive ohne Herstellerangabe.
     *
     * @param artNumber   Artikelnummer
     * @param bezeichnung Bezeichnung der Lok
     * @param typ         Typ der Lok
     * @param modell      Modell oder Baureihe
     * @param stromart    Stromart
     * @param spur        Spurweite
     * @param epoche      Epoche
     * @param betriebsart Betriebsart
     */
    public Lok(String artNumber, String bezeichnung, String typ, String modell, String stromart, String spur, String epoche, String betriebsart) {
        this.artNumber = artNumber;
        this.bezeichnung = bezeichnung;
        this.typ = typ;
        this.modell = modell;
        this.stromart = stromart;
        this.spur = spur;
        this.epoche = epoche;
        this.betriebsart = betriebsart;
    }


    //----Getter & Setter -----

    /**
     * Gibt ID zurück
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt ArtNumber zurück
     * @return artNumber
     */
    public String getArtNumber() {
        return artNumber;
    }

    /**
     * Setzt artNumber
     * @param artNumber
     */
    public void setArtNumber(String artNumber) {
        this.artNumber = artNumber;
    }

    /**
     * Gibt Bezeichnung zurück
     * @return bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Setzt bezeichung
     * @param bezeichnung
     */
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * Gibt Typ Zurük
     * @return typ
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Setzt Typ
     * @param typ
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * Gibt Modell zurück
     * @return modell
     */
    public String getModell() {
        return modell;
    }

    /**
     * Setzt Modell
     * @param modell
     */
    public void setModell(String modell) {
        this.modell = modell;
    }

    /**
     * Gibt Stromart zurück
     * @return stromart
     */
    public String getStromart() {
        return stromart;
    }

    /**
     * Setzt Stromart
     * @param stromart
     */
    public void setStromart(String stromart) {
        this.stromart = stromart;
    }

    /**
     * Gibt Spur zurück
     * @return spur
     */
    public String getSpur() {
        return spur;
    }

    /**
     * Setzt Spur
     * @param spur
     */
    public void setSpur(String spur) {
        this.spur = spur;
    }

    /**
     * Gibt Epoche zurück
     * @return epoche
     */
    public String getEpoche() {
        return epoche;
    }

    /**
     * Setzt Epoche
     * @param epoche
     */
    public void setEpoche(String epoche) {
        this.epoche = epoche;
    }

    /**
     * Gibt Betriebsart zurück
     * @return betriebsart
     */
    public String getBetriebsart() {
        return betriebsart;
    }

    /**
     * Setzt Betriebsart
     * @param betriebsart
     */
    public void setBetriebsart(String betriebsart) {
        this.betriebsart = betriebsart;
    }

    /**
     * Gibt Hersteller zurück
     * @return hersteller
     */
    public Hersteller getHersteller() {
        return hersteller;
    }

    /**
     * Setzt Hersteller
     * @param hersteller
     */
    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }
}
