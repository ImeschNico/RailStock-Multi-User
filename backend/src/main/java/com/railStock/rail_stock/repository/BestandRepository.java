package com.railStock.rail_stock.repository;

import com.railStock.rail_stock.entity.Bestand;
import com.railStock.rail_stock.entity.Lok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Schnittstelle für {@link Bestand}-Entities.
 * <p>
 * Bietet CRUD-Operationen sowie spezielle Abfragen zum Finden von Beständen
 * nach Artikelnummer, Lagerplatz oder Kombinationen davon.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Repository
public interface BestandRepository extends JpaRepository<Bestand, Long> {

    /**
     * Findet alle Bestände, die zu einer bestimmten Artikelnummer gehören.
     *
     * @param artNumber Artikelnummer der Lok
     * @return Liste von Beständen
     */
    List<Bestand> findByLok_ArtNumber(String artNumber);

    /**
     * Gibt die Summe aller Bestände über alle Lagerplätze hinweg zurück.
     * <p>
     * Diese Methode summiert die Menge aller {@link Bestand}-Einträge.
     * </p>
     *
     * @return die Gesamtmenge aller Bestände oder {@code null}, falls keine Daten vorhanden sind
     */
    @Query("SELECT SUM(b.menge) FROM Bestand b")
            Integer getBestand();

    /**
     * Findet alle Bestände an einem bestimmten Lagerplatz (Regal + Tablar).
     *
     * @param regal  Regalbezeichnung
     * @param tablar Tablarbezeichnung
     * @return Liste von Beständen an diesem Lagerplatz
     */
    List<Bestand> findByLagerplatz_RegalAndLagerplatz_Tablar(String regal, String tablar);

    /**
     * Findet einen Bestand nach Artikelnummer und Lagerplatz (Regal + Tablar),
     * wobei Artikelnummer und Lagerplatzangaben case-insensitive verglichen werden.
     *
     * @param artNumber Artikelnummer der Lok
     * @param regal     Regalbezeichnung
     * @param tablar    Tablarbezeichnung
     * @return Optional mit Bestand, falls vorhanden
     */
    @Query("SELECT b FROM Bestand b WHERE TRIM(LOWER(b.lok.artNumber)) = :artNumber " +
            "AND LOWER(b.lagerplatz.regal) = LOWER(:regal) " +
            "AND LOWER(b.lagerplatz.tablar) = LOWER(:tablar)")
    Optional<Bestand> findByLokAndLagerplatz(@Param("artNumber") String artNumber,
                                             @Param("regal") String regal,
                                             @Param("tablar") String tablar);

    /**
     * Findet alle Bestände, deren zugehörige Lok von einem bestimmten Hersteller stammt.
     * <p>
     * Diese Abfrage vergleicht den Namen des Herstellers case-insensitive.
     * </p>
     *
     * @param hersteller der Name des Herstellers
     * @return eine Liste aller {@link Bestand}-Einträge, deren Lok vom angegebenen Hersteller stammt
     */
    @Query("SELECT b FROM Bestand b WHERE LOWER(b.lok.hersteller.name) = LOWER(:name)")
    List<Bestand> findByHersteller_NameIgnoreCase(@Param("name")String hersteller);
}
