package com.railStock.rail_stock.mapper;


import com.railStock.rail_stock.dto.LagerplatzDTO;
import com.railStock.rail_stock.entity.Lagerplatz;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper-Klasse zur Umwandlung zwischen {@link Lagerplatz} Entity und {@link LagerplatzDTO}.
 * <p>
 * Diese Klasse enthält statische Methoden zum Konvertieren von Lagerplatz-Entities zu DTOs
 * und umgekehrt, sowie für die Umwandlung ganzer Listen.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class LagerplatzMapper {

    /**
     * Wandelt ein {@link Lagerplatz}-Entity in ein {@link LagerplatzDTO} um.
     *
     * @param entity Lagerplatz-Entity
     * @return LagerplatzDTO oder {@code null}, falls das Entity {@code null} ist
     */
    public static LagerplatzDTO toDTO(Lagerplatz entity) {

        if (entity == null) {
            return null;
        }

        return new LagerplatzDTO(
                entity.getId(),
                entity.getRegal(),
                entity.getTablar()
        );

    }

    /**
     * Standardkonstruktor.
     * <p>
     * Da alle Methoden statisch sind, wird dieser Konstruktor selten verwendet.
     * </p>
     */
    public LagerplatzMapper(){}

    /**
     * Wandelt ein {@link LagerplatzDTO} in ein {@link Lagerplatz}-Entity um.
     *
     * @param dto LagerplatzDTO
     * @return Lagerplatz-Entity oder {@code null}, falls das DTO {@code null} ist
     */
    public static Lagerplatz toEntity(LagerplatzDTO dto) {
        if (dto == null) {
            return null;
        }

        Lagerplatz lagerplatz = new Lagerplatz(
                dto.getRegal(),
                dto.getTablar()
        );
        lagerplatz.setId(dto.getId());
        return lagerplatz;
    }

    /**
     * Wandelt eine Liste von {@link Lagerplatz}-Entities in eine Liste von {@link LagerplatzDTO} um.
     *
     * @param lagerplaetze Liste von Lagerplatz-Entities
     * @return Liste von LagerplatzDTOs
     */
    public static List<LagerplatzDTO> toDTOList(List<Lagerplatz> lagerplaetze) {
        return lagerplaetze.stream().map(lp -> new LagerplatzDTO(lp.getId(), lp.getRegal(), lp.getTablar())).collect(Collectors.toList());
    }
}
