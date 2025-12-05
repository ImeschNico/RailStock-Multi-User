package com.railStock.rail_stock.mapper;

import com.railStock.rail_stock.dto.HerstellerDTO;
import com.railStock.rail_stock.entity.Hersteller;

/**
 * Mapper-Klasse zur Umwandlung von {@link Hersteller}-Entities in {@link HerstellerDTO}-Objekte.
 * <p>
 * Diese Klasse stellt statische Methoden bereit, um Entitäten aus der
 * Datenbank in übertragbare DTO-Objekte umzuwandeln. Dadurch wird sichergestellt,
 * dass nur relevante Daten an das Frontend weitergegeben werden.
 * </p>
 *
 * <p>
 * Mapper-Klassen tragen zur sauberen Trennung von Daten- und Darstellungsschicht bei.
 * </p>
 * @author Nico Imesch
 *
 * @version 1.0
 */
public class HerstellerMapper {

    /**
     * Wandelt ein {@link Hersteller}-Entity in ein {@link HerstellerDTO} um.
     * <p>
     * Diese Methode extrahiert die relevanten Felder (ID und Name)
     * und erstellt daraus ein neues {@link HerstellerDTO}-Objekt.
     * </p>
     *
     * @param hersteller das {@link Hersteller}-Entity, das umgewandelt werden soll
     * @return ein neues {@link HerstellerDTO} mit den entsprechenden Daten
     */
    public static HerstellerDTO toDTO(Hersteller hersteller) {
        return new HerstellerDTO(hersteller.getId(), hersteller.getName());
    }
}
