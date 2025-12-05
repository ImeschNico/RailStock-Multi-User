package com.railStock.rail_stock.repository;

import com.railStock.rail_stock.entity.Lok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Schnittstelle für {@link Lok}-Entities.
 * <p>
 * Bietet CRUD-Operationen sowie spezielle Abfragen zum Finden von Loks
 * anhand ihrer Artikelnummer.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Repository
public interface LokRepository extends JpaRepository<Lok, Long> {

    /**
     * Findet eine Lok anhand ihrer Artikelnummer.
     *
     * @param artNumber Artikelnummer der Lok
     * @return Optional mit Lok, falls vorhanden
     */
    Optional<Lok> findByArtNumber(String artNumber);

}
