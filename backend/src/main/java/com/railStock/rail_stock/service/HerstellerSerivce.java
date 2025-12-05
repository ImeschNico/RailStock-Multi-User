package com.railStock.rail_stock.service;


import com.railStock.rail_stock.entity.Hersteller;
import com.railStock.rail_stock.repository.HerstellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse für Operationen auf {@link Hersteller}-Entities.
 * <p>
 * Diese Klasse kapselt alle Operationen, die auf Hersteller-Daten ausgeführt werden,
 * und dient als Vermittlungsschicht zwischen Controller und Repository.
 * Dadurch wird eine klare Trennung zwischen Präsentations- und Datenzugriffsschicht erreicht.
 * </p>
 *
 * <p><b>Typische Aufgaben:</b></p>
 * <ul>
 *     <li>Abrufen aller Hersteller aus der Datenbank</li>
 *     <li>Suchen eines bestimmten Herstellers anhand seines Namens</li>
 * </ul>
 * Autor: Nico Imesch
 * Version: 1.0
 */
@Service
public class HerstellerSerivce {

    private final HerstellerRepository herstellerRepository;

    /**
     * Konstruktor für die Dependency Injection.
     *
     * @param herstellerRepository Repository für Hersteller
     */
    public HerstellerSerivce(HerstellerRepository herstellerRepository) {
        this.herstellerRepository = herstellerRepository;
    }

    /**
     * Findet einen Hersteller anhand des Namens.
     *
     * @param name Name des Herstellers
     * @return Gefundener Hersteller oder null, falls nicht vorhanden
     */
    public Hersteller findHerstellerByName(String name) {
        return herstellerRepository.findByName(name);
    }


    /**
     * Ruft alle in der Datenbank gespeicherten Hersteller ab.
     *
     * @return eine {@link List} aller {@link Hersteller}-Entities
     */
    public List<Hersteller> findAllHerstellers() {
        return herstellerRepository.findAll();
    }
}
