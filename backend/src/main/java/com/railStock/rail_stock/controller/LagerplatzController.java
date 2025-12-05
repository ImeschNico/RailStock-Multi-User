package com.railStock.rail_stock.controller;


import com.railStock.rail_stock.dto.LagerplatzDTO;
import com.railStock.rail_stock.entity.Lagerplatz;
import com.railStock.rail_stock.mapper.LagerplatzMapper;
import com.railStock.rail_stock.service.LagerplatzService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller zur Verwaltung von Lagerplätzen.
 * <p>
 * Diese Klasse stellt API-Endpunkte bereit, um Lagerplätze zu filtern und neue
 * Lagerplätze zu erstellen. Die Business-Logik wird durch die
 * {@link LagerplatzService}-Klasse umgesetzt.
 * </p>
 *
 * <p><b>Base Path:</b> {@code /api/lagerplatz}</p>
 *
 * <p>
 * Die Rückgabe erfolgt in Form von {@link LagerplatzDTO}-Objekten, um die
 * Entitäten vom API-Layer zu trennen.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
@RestController
@RequestMapping("/api/lagerplatz")
public class LagerplatzController {

    private final LagerplatzService lagerplatzService;

    /**
     * Konstruktor zur Initialisierung des Controllers mit dem zugehörigen Service.
     *
     * @param lagerplatzService Service-Klasse zur Verwaltung von Lagerplätzen
     */
    public LagerplatzController(LagerplatzService lagerplatzService) {
        this.lagerplatzService = lagerplatzService;
    }

    /**
     * Gibt eine Liste von Lagerplätzen basierend auf optionalen Filterkriterien zurück.
     * <p>
     * Wenn kein Filter angegeben ist, werden alle Lagerplätze zurückgegeben.
     * </p>
     *
     * @param regal  optionaler Filterparameter für das Regal
     * @param tablar optionaler Filterparameter für das Tablar (Fach)
     * @return Liste von {@link LagerplatzDTO}-Objekten, die den Filterkriterien entsprechen
     */
    @Operation(summary = "Filtert Lagerplätze nach Regal und/oder Tablar")
    @GetMapping("/filter")
    public List<LagerplatzDTO> getLagerplaetze(
            @RequestParam(required = false) String regal,
            @RequestParam(required = false) String tablar
    ){
        List<Lagerplatz> lagerplaetze = lagerplatzService.findByFilter(regal, tablar);
        return LagerplatzMapper.toDTOList(lagerplaetze);
    }

    /**
     * Erstellt einen neuen Lagerplatz in der Datenbank.
     * <p>
     * Der übergebene {@link LagerplatzDTO} enthält die notwendigen Informationen
     * für die Erstellung (z. B. Regal, Tablar, Kapazität, etc.).
     * </p>
     *
     * @param dto Datenobjekt mit den Eigenschaften des neuen Lagerplatzes
     * @return das erstellte {@link LagerplatzDTO}-Objekt
     */
    @Operation(summary = "Erstellt einen neuen Lagerplatz")
    @PostMapping("/erstellen")
    public LagerplatzDTO erstellenLagerplatz(@RequestBody LagerplatzDTO dto){
        Lagerplatz lp = lagerplatzService.create(dto);
        return LagerplatzMapper.toDTO(lp);
    }
}
