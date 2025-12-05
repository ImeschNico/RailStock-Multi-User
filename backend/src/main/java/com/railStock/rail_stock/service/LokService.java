package com.railStock.rail_stock.service;


import com.railStock.rail_stock.dto.LokDTO;
import com.railStock.rail_stock.dto.LokFormDTO;
import com.railStock.rail_stock.entity.Hersteller;
import com.railStock.rail_stock.entity.Lok;
import com.railStock.rail_stock.mapper.LokMapper;
import com.railStock.rail_stock.repository.HerstellerRepository;
import com.railStock.rail_stock.repository.LagerplatzRepository;
import com.railStock.rail_stock.repository.LokRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service-Klasse für Operationen auf {@link Lok}-Entities.
 * <p>
 * Bietet Methoden zum Abrufen und Filtern von Lokomotiven.
 * </p>
 *
 * Autor: Nico Imesch
 * Version: 1.0
 */
@Service
public class LokService {

    private final HerstellerRepository herstellerRepository;
    private final LokRepository lokRepository;

    /**
     * Konstruktor für Dependency Injection.
     *
     * @param lokRepository Repository für Loks
     */
    public LokService(LokRepository lokRepository, HerstellerRepository herstellerRepository) {
        this.lokRepository = lokRepository;
        this.herstellerRepository = herstellerRepository;

    }

    public LokDTO createLok(LokFormDTO dto){
       Lok lok = new Lok(
               dto.getArtNumber(),
               dto.getBezeichnung(),
               dto.getTyp(),
               dto.getModell(),
               dto.getStromart(),
               dto.getSpur(),
               dto.getEpoche(),
               dto.getBetriebsart()
       );
        Hersteller hersteller = herstellerRepository.findByName(dto.getHerstellerName());
        if (hersteller == null) {
            throw new IllegalArgumentException("Hersteller mit Namen '" + dto.getHerstellerName() + "' existiert nicht");
        }
        lok.setHersteller(hersteller);
       Lok saved = lokRepository.save(lok);
       return LokMapper.toDTO(saved);
    }

    /**
     * Filtert Loks nach verschiedenen optionalen Kriterien.
     *
     * @param artNumber     Art-Nummer der Lok, optional
     * @param bezeichnung   Bezeichnung der Lok, optional
     * @param typ           Typ der Lok, optional
     * @param modell        Modellbezeichnung, optional
     * @param stromart      Stromart, optional
     * @param spur          Spur, optional
     * @param epoche        Epoche, optional
     * @param betriebsart   Betriebsart, optional
     * @param herstellerName Name des Herstellers, optional
     * @return Liste von {@link LokDTO}, die den Filterkriterien entsprechen
     */
    public List<LokDTO> filterLoks(
            String artNumber,
            String bezeichnung,
            String typ,
            String modell,
            String stromart,
            String spur,
            String epoche,
            String betriebsart,
            String herstellerName
    ){
        List<Lok> loks = lokRepository.findAll();

        return loks.stream()
                .filter(l -> artNumber == null || l.getArtNumber().equalsIgnoreCase(artNumber))
                .filter(l -> bezeichnung == null || l.getBezeichnung().equalsIgnoreCase(bezeichnung))
                .filter(l -> typ == null || l.getTyp().equalsIgnoreCase(typ))
                .filter(l -> modell == null || l.getModell().equalsIgnoreCase(modell))
                .filter(l -> stromart == null|| l.getStromart().equalsIgnoreCase(stromart))
                .filter(l -> spur == null || l.getSpur().equalsIgnoreCase(spur))
                .filter(l -> epoche == null || l.getEpoche().equalsIgnoreCase(epoche))
                .filter(l -> betriebsart == null || l.getBetriebsart().equalsIgnoreCase(betriebsart))
                .filter(l -> herstellerName == null || l.getHersteller().getName().equalsIgnoreCase(herstellerName))
                .map(LokMapper::toDTO)
                .collect(Collectors.toList());
    }
}
