package com.railStock.rail_stock.service;


import com.railStock.rail_stock.dto.LagerplatzDTO;
import com.railStock.rail_stock.entity.Lagerplatz;
import com.railStock.rail_stock.mapper.LagerplatzMapper;
import com.railStock.rail_stock.repository.LagerplatzRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service-Klasse für Operationen auf {@link Lagerplatz}-Entities.
 * <p>
 * Bietet Methoden zum Abrufen, Filtern und Anlegen von Lagerplätzen.
 * </p>
 *
 * Autor: Nico Imesch
 * Version: 1.0
 */
@Service
public class LagerplatzService {

    private final LagerplatzRepository lagerplatzRepository;

    /**
     * Konstruktor für Dependency Injection.
     *
     * @param lagerplatzRepository Repository für Lagerplätze
     */
    public LagerplatzService(LagerplatzRepository lagerplatzRepository) {
        this.lagerplatzRepository = lagerplatzRepository;
    }


    /**
     * Findet Lagerplätze anhand optionaler Filterkriterien.
     *
     * @param regal  Regalbezeichnung, optional
     * @param tablar Tablarbezeichnung, optional
     * @return Liste von Lagerplätzen, die den Filterkriterien entsprechen
     */
    public List<Lagerplatz> findByFilter(String regal, String tablar) {
        return lagerplatzRepository.findByFilter(regal, tablar);
    }

    /**
     * Legt einen neuen Lagerplatz an, falls dieser noch nicht existiert.
     *
     * @param dto DTO mit Regal- und Tablarangaben
     * @return Existierender oder neu angelegter Lagerplatz
     */
    public Lagerplatz create(LagerplatzDTO dto) {
        return lagerplatzRepository.findByRegalAndTablarIgnoreCase(dto.getRegal(), dto.getTablar())
                .orElseGet(() -> {
                    Lagerplatz neu = LagerplatzMapper.toEntity(dto);
                    return lagerplatzRepository.save(neu);
                });
    }
}
