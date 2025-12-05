package com.railStock.rail_stock.controller;


import com.railStock.rail_stock.dto.HerstellerDTO;
import com.railStock.rail_stock.entity.Hersteller;
import com.railStock.rail_stock.mapper.HerstellerMapper;
import com.railStock.rail_stock.service.BestandService;
import com.railStock.rail_stock.service.HerstellerSerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-Controller zur Verwaltung und Abfrage von Hersteller.
 * <p>
 *  Diese Klasse stellt Endpunkte zur Verfügung, über die das Frontend alle
 *  Hersteller aus der Datenbank abrufen kann. Die Daten werden mithilfe
 *  des {@link HerstellerMapper} in {@link HerstellerDTO}-Objekte konvertiert,
 *  um nur die relevanten Informationen zu übertragen.
 * </p>
 *<p>
 *  Der Controller kommuniziert ausschließlich über den
 *  {@link HerstellerSerivce}, wodurch die Trennung von Controller- und
 *  Geschäftslogik sichergestellt wird.
 *  </p>
 *
 *  <p><b>Endpunkt:</b> {@code /api/hersteller}</p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@RestController
public class HerstellerController {

    /**
     * Service-Klasse für Hersteller-Operationen.
     */
    private final HerstellerSerivce herstellerSerivce;

    /**
     * Konstruktor für die Dependency Injection.
     *
     * @param herstellerSerivce Service für den Zugriff auf Herstellerdaten
     */
    public HerstellerController(HerstellerSerivce herstellerSerivce) {
        this.herstellerSerivce = herstellerSerivce;
    }

    /**
     * Ruft alle in der Datenbank gespeicherten Hersteller ab.
     * <p>
     * Diese Methode wird typischerweise vom Frontend verwendet,
     * um eine Liste aller Hersteller (z. B. für Filter- oder Auswahlfunktionen)
     * anzuzeigen. Die {@link Hersteller}-Entities werden dazu in
     * {@link HerstellerDTO}-Objekte umgewandelt.
     * </p>
     *
     * @return Liste aller Hersteller als {@link HerstellerDTO}
     */
    @GetMapping("/api/hersteller")
    public List<HerstellerDTO> getAllHersteller() {
        List<Hersteller> herstellerList = herstellerSerivce.findAllHerstellers();
        return herstellerList.stream().map(HerstellerMapper::toDTO)
                .collect(Collectors.toList());
    }

}
