package com.railStock.rail_stock.service;


import com.railStock.rail_stock.ErrorHandling.NotFoundException;
import com.railStock.rail_stock.dto.BestandDTO;
import com.railStock.rail_stock.dto.BestandFormDTO;
import com.railStock.rail_stock.dto.GesamtBestandDTO;
import com.railStock.rail_stock.entity.Bestand;
import com.railStock.rail_stock.entity.Lagerplatz;
import com.railStock.rail_stock.entity.Lok;
import com.railStock.rail_stock.mapper.BestandMapper;
import com.railStock.rail_stock.repository.BestandRepository;
import com.railStock.rail_stock.repository.LagerplatzRepository;
import com.railStock.rail_stock.repository.LokRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service-Klasse für Operationen auf {@link Bestand}-Entities.
 * <p>
 * Bietet Methoden zum Abrufen, Aktualisieren und Transferieren von Beständen
 * sowie zur Berechnung des Gesamtbestands.
 * </p>
 *
 * Autor: Nico Imesch
 * Version: 1.0
 */
@Service
public class BestandService {

    private final BestandRepository bestandRepository;
    private final LagerplatzRepository lagerplatzRepository;
    private final LokRepository lokRepository;


    public BestandService(BestandRepository betsandRepository, LagerplatzRepository lagerplatzRepository, LokRepository lokRepository) {
        this.bestandRepository = betsandRepository;
        this.lagerplatzRepository = lagerplatzRepository;
        this.lokRepository = lokRepository;

    }

    /**
     * Liefert alle Bestände.
     *
     * @return Liste aller Bestände
     */
    public List<Bestand> findAll() {
        return bestandRepository.findAll();
    }


    public int getAllBestandAsInt() {
        Integer sum = bestandRepository.getBestand();
        return sum != null ? sum : 0;
    }

    /**
     * Findet Bestände anhand der Artikelnummer.
     *
     * @param artNumber Artikelnummer der Lok
     * @return Liste von Beständen
     */
    public List<Bestand> findByArtNumber(String artNumber) {
        return bestandRepository.findByLok_ArtNumber(artNumber);
    }

    /**
     * Aktualisiert einen Bestand basierend auf dem übergebenen DTO.
     * <p>
     * Falls der Bestand noch nicht existiert, wird ein neuer Eintrag angelegt.
     * </p>
     *
     * @param form BestandFormDTO mit ArtNumber, Regal, Tablar und Menge
     * @return Aktualisierter Bestand
     * @throws RuntimeException wenn Lagerplatz oder Lok nicht gefunden wird
     */
    public Bestand updateBestand(BestandFormDTO form) {

        // Werte aus DTO
        String lokArtNumber = form.getArtNumber();
        String regal = form.getRegal();
        String tablar = form.getTablar();
        int menge = form.getMenge();

        // Lagerplatz prüfen
        Lagerplatz lagerplatz = lagerplatzRepository
                .findByRegalAndTablarIgnoreCase(regal, tablar)
                .orElseThrow(() -> new RuntimeException("Lagerplatz nicht gefunden"));

        // Bestand suchen
        Bestand bestand = bestandRepository
                .findByLokAndLagerplatz(lokArtNumber, regal, tablar)
                .orElseGet(() -> {
                    Lok lok = lokRepository.findByArtNumber(lokArtNumber)
                            .orElseThrow(() -> new RuntimeException("Lok nicht gefunden"));

                    Bestand neu = new Bestand();
                    neu.setLok(lok);
                    neu.setLagerplatz(lagerplatz);
                    neu.setMenge(0);
                    return bestandRepository.save(neu);
                });

        // Menge aktualisieren
        bestand.setMenge(bestand.getMenge()+menge);
        return bestandRepository.save(bestand);
    }

    /**
     * Findet Bestände an einem bestimmten Lagerplatz.
     *
     * @param regal  Regalbezeichnung
     * @param tablar Tablarbezeichnung
     * @return Liste von Beständen an diesem Lagerplatz
     */
    public List<Bestand> findByLagerplatz(String regal, String tablar) {
        return bestandRepository.findByLagerplatz_RegalAndLagerplatz_Tablar(regal, tablar);
    }

    /**
     * Berechnet den Gesamtbestand pro Artikelnummer.
     *
     * @return Liste von GesamtBestandDTO mit ArtNumber und Gesamtmenge
     */
    public List<GesamtBestandDTO> getGesamtBestand() {
        return bestandRepository.findAll().stream().collect(Collectors.groupingBy(b -> b.getLok().getArtNumber(),
                Collectors.summingInt(Bestand:: getMenge)
        ))
                .entrySet().stream().map(e -> new GesamtBestandDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Findet Bestände nach Artikelnummer, inklusive eines "Fake"-Bestands,
     * falls kein Bestand vorhanden ist.
     *
     * @param artNumber Artikelnummer der Lok
     * @return Liste von BestandDTO
     * @throws RuntimeException wenn die Lok nicht gefunden wird
     */
    public List<BestandDTO> findByArtNumberIncludingEmpty(String artNumber) {
        Lok lok = lokRepository.findByArtNumber(artNumber)
                .orElseThrow(() -> new RuntimeException("Lok nicht gefunden"));

        List<Bestand> bestaende = bestandRepository.findByLok_ArtNumber(artNumber);

        if(bestaende.isEmpty()) {
            // Bestand = 0, Lagerplatz null oder Standard
            Bestand fakeBestand = new Bestand();
            fakeBestand.setLok(lok);
            fakeBestand.setMenge(0);
            Lagerplatz legacyLp = new Lagerplatz();
            legacyLp.setRegal("LEGACY");
            legacyLp.setTablar("LEGACY");
            fakeBestand.setLagerplatz(legacyLp);
            bestaende.add(fakeBestand);
        }

        return bestaende.stream()
                .map(BestandMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BestandDTO> getBestandByHersteller(String herstellerName){
        List<Bestand> herstellerBestand = bestandRepository.findByHersteller_NameIgnoreCase(herstellerName);
        return herstellerBestand.stream().map(BestandMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Transferiert Bestand von einem Lagerplatz zu einem anderen.
     *
     * @param artNumber Artikelnummer der Lok
     * @param vonRegal  Quell-Regal
     * @param vonTablar Quell-Tablar
     * @param zuRegal   Ziel-Regal
     * @param zuTablar  Ziel-Tablar
     * @param menge     Menge zum Transferieren
     * @return Aktualisierter Bestand am Ziel
     * @throws RuntimeException wenn Lagerplatz, Lok oder Bestand nicht gefunden wird
     *                          oder nicht genügend Bestand vorhanden ist
     */
    @Transactional
    public Bestand transferBestand(String artNumber, String vonRegal, String vonTablar, String zuRegal, String zuTablar, int menge) {


        Lagerplatz vonLp = lagerplatzRepository.findByRegalAndTablarIgnoreCase(vonRegal, vonTablar)
                .orElseThrow(() -> new RuntimeException("Lagerplatz nicht gefunden"));

        Lagerplatz zuLp = lagerplatzRepository.findByRegalAndTablarIgnoreCase(zuRegal, zuTablar)
                .orElseThrow(() -> new NotFoundException("Ziel Lagerplatz " + zuRegal + "-" + zuTablar +"existiert nicht"));


        Lok lok = lokRepository.findByArtNumber(artNumber).orElseThrow(() -> new RuntimeException("Lok nicht gefunden"));

        Bestand vonBestand = bestandRepository.findByLokAndLagerplatz(artNumber, vonRegal, vonTablar)
                .orElseThrow(() -> new RuntimeException("Bestand nicht gefunden"));

        if (vonBestand.getMenge() < menge){
            throw new RuntimeException("Nicht genug Bestand zum transferieren");
        }

        int neuMengeQuelle = vonBestand.getMenge()- menge;
        if (neuMengeQuelle == 0){
            bestandRepository.delete(vonBestand);
        }else {
            vonBestand.setMenge(neuMengeQuelle);
            bestandRepository.save(vonBestand);
        }

        Bestand zuBestand = bestandRepository.findByLokAndLagerplatz(artNumber,zuRegal,zuTablar)
                .orElseGet(() -> {
                    Bestand neu = new Bestand();
                    neu.setLok(lok);
                    neu.setLagerplatz(zuLp);
                    neu.setMenge(0);
                    return neu;
                });

        zuBestand.setMenge(zuBestand.getMenge()+menge);
        Bestand saved = bestandRepository.saveAndFlush(zuBestand);
        return saved;
    }

}
