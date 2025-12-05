package com.railStock.rail_stock.mapper;


import com.railStock.rail_stock.dto.LokDTO;
import com.railStock.rail_stock.entity.Hersteller;
import com.railStock.rail_stock.entity.Lok;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper-Klasse zur Umwandlung zwischen {@link Lok} Entity und {@link LokDTO}.
 * <p>
 * Diese Klasse enthält statische Methoden zum Konvertieren von Lok-Entities zu DTOs
 * und umgekehrt, einschließlich der zugehörigen Herstellerinformationen.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class LokMapper {

    /**
     * Wandelt ein {@link Lok}-Entity in ein {@link LokDTO} um.
     *
     * @param entity Lok-Entity
     * @return LokDTO oder {@code null}, falls das Entity {@code null} ist
     */
    public static LokDTO toDTO(Lok entity) {
        if (entity == null) {
            return null;
        }
        return new LokDTO(
                entity.getId(),
                entity.getArtNumber(),
                entity.getBezeichnung(),
                entity.getTyp(),
                entity.getModell(),
                entity.getStromart(),
                entity.getSpur(),
                entity.getEpoche(),
                entity.getBetriebsart(),
                entity.getHersteller() != null ? entity.getHersteller().getId() : null,
                entity.getHersteller() != null ? entity.getHersteller().getName() : null

        );
    }

    /**
     * Standardkonstruktor.
     * <p>
     * Da alle Methoden statisch sind, wird dieser Konstruktor selten verwendet.
     * </p>
     */
    public LokMapper() {
    }

    /**
     * Wandelt ein {@link LokDTO} in ein {@link Lok}-Entity um.
     *
     * <p>
     * Falls Herstellerinformationen im DTO vorhanden sind, wird ein {@link Hersteller}-Objekt erzeugt
     * und der Lok zugewiesen.
     * </p>
     *
     * @param dto LokDTO
     * @return Lok-Entity oder {@code null}, falls das DTO {@code null} ist
     */
    public static Lok toEntity(LokDTO dto) {
        if (dto == null) {
            return null;
        }
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
        lok.setId(dto.getId());

        if (dto.getHerstellerId() != null || dto.getHerstellerName() != null) {
            Hersteller hersteller = new Hersteller();
            hersteller.setId(dto.getHerstellerId());
            hersteller.setName(dto.getHerstellerName());
            lok.setHersteller(hersteller);
        }
        return lok;
    }

}
