package com.railStock.rail_stock.repository;


import com.railStock.rail_stock.entity.Hersteller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository-Schnittstelle für {@link Hersteller}-Entities.
 * <p>
 * Bietet CRUD-Operationen sowie spezielle Abfragen zum Finden eines Herstellers
 * anhand seines Namens.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Repository
public interface HerstellerRepository extends JpaRepository<Hersteller, Long> {

    /**
     * Findet einen Hersteller anhand seines Namens.
     *
     * @param name Name des Herstellers
     * @return Hersteller-Entity oder {@code null}, falls kein Hersteller mit diesem Namen existiert
     */
    Hersteller findByName(String name);
}
