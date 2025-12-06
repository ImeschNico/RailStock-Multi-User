package com.railStock.rail_stock.controller;


import com.railStock.rail_stock.dto.LokDTO;
import com.railStock.rail_stock.dto.LokFormDTO;
import com.railStock.rail_stock.entity.Lok;
import com.railStock.rail_stock.service.LokService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller zur Verwaltung und Abfrage von Lokomotiven.
 * <p>
 * Diese Klasse stellt API-Endpunkte bereit, um Lokdaten anhand verschiedener
 * Filterkriterien (z. B. Artikelnummer, Bezeichnung, Hersteller usw.) abzurufen.
 * Die eigentliche Geschäftslogik wird in {@link LokService} umgesetzt.
 * </p>
 *
 * <p><b>Base Path:</b> {@code /api/loks}</p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@Tag(name = "Lokverwaltung", description = "API-Endpunkte zur Filterung und Verwaltung von Lokomotiven")
@RestController
@RequestMapping("/api/loks")
public class LokController {

    private final LokService lokService;

    /**
     * Konstruktor zur Initialisierung des Controllers mit dem zugehörigen Service.
     *
     * @param lokService Service-Klasse zur Verwaltung der Lokomotiven
     */
    public LokController(LokService lokService) {
        this.lokService = lokService;
    }

    /**
     * Filtert Lokomotiven anhand der angegebenen Parameter.
     * <p>
     * Alle Parameter sind optional. Wenn kein Filter gesetzt ist, werden alle
     * Lokomotiven zurückgegeben.
     * </p>
     *
     * @param artNumber       optionale Artikelnummer der Lok
     * @param bezeichnung     optionale Bezeichnung der Lok
     * @param typ             optionaler Loktyp (z. B. „E-Lok“, „Diesellok“)
     * @param modell          optionales Modell oder Baureihe
     * @param stromart        optionale Stromart (z. B. „AC“, „DC“, „Digital“)
     * @param spur            optionale Spurweite (z. B. „H0“, „N“)
     * @param epoche          optionale Eisenbahnepoche (z. B. „III“, „IV“)
     * @param betriebsart     optionale Betriebsart (z. B. „Analog“, „Digital“)
     * @param herstellerName  optionaler Herstellername
     * @return Liste von {@link LokDTO}-Objekten, die den Filterkriterien entsprechen
     */
    @Operation(summary = "Filtert Lokomotiven nach verschiedenen Kriterien")
    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<LokDTO> filterLoks(
            @RequestParam(required = false) String artNumber,
            @RequestParam(required = false) String bezeichnung,
            @RequestParam(required = false) String typ,
            @RequestParam(required = false) String modell,
            @RequestParam(required = false) String stromart,
            @RequestParam(required = false) String spur,
            @RequestParam(required = false) String epoche,
            @RequestParam(required = false) String betriebsart,
            @RequestParam(required = false) String herstellerName
    ){
        return lokService.filterLoks(artNumber, bezeichnung, typ, modell, stromart, spur, epoche, betriebsart, herstellerName);
    }

    /**
     * ERstellt neue Loks
     * @param dto
     * @return neu erstelllte Lok anahnd der Eingaben
     */
    @PostMapping("/admin/neu")
    @PreAuthorize("hasRole('ADMIN')")
    public LokDTO createLok(@RequestBody LokFormDTO dto){
        return lokService.createLok(dto);
    }

}
