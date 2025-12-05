package com.railStock.rail_stock.repository;

import com.railStock.rail_stock.entity.Lagerplatz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Schnittstelle für {@link Lagerplatz}-Entities.
 * <p>
 * Bietet CRUD-Operationen sowie spezielle Abfragen zum Finden von Lagerplätzen
 * anhand von Regal, Tablar oder flexiblen Filterkriterien.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Repository
public interface LagerplatzRepository extends JpaRepository<Lagerplatz, Long> {

    /**
     * Findet alle Lagerplätze in einem bestimmten Regal (case-insensitive).
     *
     * @param regal Regalbezeichnung
     * @return Liste von Lagerplätzen im angegebenen Regal
     */
    List<Lagerplatz> findByRegalIgnoreCase(String regal);

    /**
     * Findet alle Lagerplätze mit einem bestimmten Tablar (case-insensitive, getrimmte Werte).
     *
     * @param tablar Tablarbezeichnung
     * @return Liste von Lagerplätzen mit dem angegebenen Tablar
     */
    @Query("SELECT l FROM Lagerplatz l WHERE TRIM(LOWER(l.tablar)) = LOWER(:tablar)")
    List<Lagerplatz> findByTablarIgnoreCaseTrim(@Param("tablar") String tablar);

    /**
     * Findet einen Lagerplatz anhand von Regal und Tablar (case-insensitive).
     *
     * @param regal  Regalbezeichnung
     * @param tablar Tablarbezeichnung
     * @return Optional mit Lagerplatz, falls vorhanden
     */
    Optional<Lagerplatz> findByRegalAndTablarIgnoreCase(String regal, String tablar);

    /**
     * Findet Lagerplätze anhand flexibler Filterkriterien.
     * <p>
     * Ist ein Parameter {@code null}, wird er ignoriert.
     * </p>
     *
     * @param regal  Optionales Regal
     * @param tablar Optionales Tablar
     * @return Liste von Lagerplätzen, die den Filterkriterien entsprechen
     */
    @Query("SELECT l FROM Lagerplatz l " +
            "WHERE (:regal IS NULL OR LOWER(l.regal) = LOWER(:regal)) " +
            "AND (:tablar IS NULL OR LOWER(l.tablar) = LOWER(:tablar))")
    List<Lagerplatz> findByFilter(@Param("regal") String regal, @Param("tablar") String tablar);
}