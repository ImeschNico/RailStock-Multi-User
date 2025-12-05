package com.railStock.rail_stock.controller;


import com.railStock.rail_stock.ErrorHandling.NotFoundException;
import com.railStock.rail_stock.dto.BestandDTO;
import com.railStock.rail_stock.dto.BestandFormDTO;
import com.railStock.rail_stock.dto.BestandTransferDTO;
import com.railStock.rail_stock.dto.GesamtBestandDTO;
import com.railStock.rail_stock.entity.Bestand;
import com.railStock.rail_stock.entity.Lok;
import com.railStock.rail_stock.mapper.BestandMapper;
import com.railStock.rail_stock.service.BestandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-Controller zur Verwaltung und Abfrage von Beständen.
 * <p>
 * Diese Klasse stellt die API-Endpunkte für den Zugriff auf Lagerbestände,
 * Lagerplätze und Bestandsbewegungen bereit. Sie kommuniziert mit der
 * {@link BestandService}-Schicht, um Geschäftslogik und Datenbankzugriffe
 * zu kapseln.
 * </p>
 *
 * <p><b>Base Path:</b> {@code /api/bestand}</p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@RestController
@RequestMapping("/api/bestand")
public class BestandController {

    private final BestandService bestandService;

    /**
     * Konstruktor zur Initialisierung des Controllers mit dem zugehörigen Service.
     *
     * @param bestandService Service-Klasse zur Verwaltung von Beständen
     */
    public BestandController(BestandService bestandService) {
        this.bestandService = bestandService;
    }

    /**
     * Gibt alle Bestände für eine bestimmte Lok-Artikelnnummer zurück.
     * Auch Lagerplätze ohne Bestandseintrag (leere Plätze) werden berücksichtigt.
     *
     * @param artNumber die Artikelnummer der Lok
     * @return Liste von {@link BestandDTO}-Objekten mit Bestandsinformationen
     */
    @GetMapping("/lok/{artNumber}")
    public List<BestandDTO> getBestand(@PathVariable String artNumber) {
        return bestandService.findByArtNumberIncludingEmpty(artNumber);
    }

    /**
     * Liefert den Betsand nach Hersteller
     * @param herstellerName der Name des Herstellers
     * @return Liste des Bestandes nach Hersteller
     */
    @GetMapping("/hersteller/{herstellerName}")
    public List<BestandDTO> findBestandByHersteller(@PathVariable String herstellerName) {
        return bestandService.getBestandByHersteller(herstellerName);
    }

    /**
     * Aktualisiert den Bestand anhand der übergebenen Formulardaten.
     *
     * @param form {@link BestandFormDTO} mit den neuen Bestandswerten
     * @return das aktualisierte {@link BestandDTO}-Objekt
     */
    @PutMapping("/updateBestand")
    public BestandDTO updateBestand(@RequestBody BestandFormDTO form) {
        Bestand updated = bestandService.updateBestand(form);
        return BestandMapper.toDTO(updated);
    }



    /**
     * Liefert eine Übersicht über alle vorhandenen Bestände im System.
     *
     * @return Liste von {@link GesamtBestandDTO}-Objekten mit aggregierten Bestandsdaten
     */
    @GetMapping("/alleBestaende")
    public List<GesamtBestandDTO> getGesamtBestand() {
        return bestandService.getGesamtBestand();
    }

    /**
     * Gibt alle Bestände an einem bestimmten Lagerplatz zurück.
     *
     * @param regal  das Regal, in dem sich der Bestand befindet
     * @param tablar das Tablar (Fach) im Regal
     * @return Liste von {@link BestandDTO}-Objekten am angegebenen Lagerplatz
     */
    @GetMapping("/lagerplatz")
    public List<BestandDTO> getAllBestand(@PathVariable String regal, String tablar) {
        return bestandService.findByLagerplatz(regal, tablar).stream().map(BestandMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Überträgt einen bestimmten Bestand von einem Lagerplatz zu einem anderen.
     * <p>
     * Diese Methode wird verwendet, um Bestände physisch zwischen Regalen oder
     * Fächern zu verschieben.
     * </p>
     *
     * @param form {@link BestandTransferDTO} mit Quell- und Zielinformationen
     * @return das aktualisierte {@link BestandDTO}-Objekt nach dem Transfer
     */
    @PutMapping("/transferBestand")
    public BestandDTO transferBestand(@RequestBody BestandTransferDTO form) {
        Bestand transferred = bestandService.transferBestand(
                form.getArtNumber(),
                form.getVonRegal(),
                form.getVonTablar(),
                form.getZuRegal(),
                form.getZuTablar(),
                form.getMenge()
        );
        return BestandMapper.toDTO(transferred);
    }

    /**
     * Liefert alle Bestände damit man einen Überblick über alle Bestände hat
     * @return Alle Bestände als eine Nummer(Integer)
     */
    @GetMapping("/alle")
    public ResponseEntity<Integer> getAllBestandAsInt(){
        return ResponseEntity.ok(bestandService.getAllBestandAsInt());
    }

    /**
     * Fehler Handler, um richtige Fehler im Dev Tool anzuzeigen
     * @param ex
     * @return Not Found status
     */
    @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<String> handleNotFound(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }
}
