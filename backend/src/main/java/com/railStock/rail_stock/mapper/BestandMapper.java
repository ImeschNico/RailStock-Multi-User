package com.railStock.rail_stock.mapper;


import com.railStock.rail_stock.dto.BestandDTO;
import com.railStock.rail_stock.entity.Bestand;
import com.railStock.rail_stock.entity.Hersteller;

/**
 * Mapper-Klasse zur Umwandlung zwischen {@link Bestand} Entity und {@link BestandDTO}.
 * <p>
 * Diese Klasse enthält statische Methoden zum Konvertieren von Entities zu DTOs
 * und umgekehrt, um die Trennung zwischen Persistenz- und Präsentationsschicht zu gewährleisten.
 * </p>
 *
 * @author Nico Imesch
 * @version 1.0
 */
public class BestandMapper {

    /**
     * Wandelt ein {@link Bestand}-Entity in ein {@link BestandDTO} um.
     *
     * @param entity Bestand-Entity
     * @return BestandDTO oder {@code null}, falls das Entity {@code null} ist
     */
    public static BestandDTO toDTO(Bestand entity) {
        if (entity == null) {
            return null;
        }

        return new BestandDTO(
                entity.getId(),
                entity.getLok() != null ? LokMapper.toDTO(entity.getLok()) : null,
                entity.getLagerplatz() != null ? LagerplatzMapper.toDTO(entity.getLagerplatz()) : null,
                entity.getMenge(),
                entity.getHersteller() != null ? entity.getHersteller().getName() : null

        );
    }

    /**
     * Standardkonstruktor.
     * <p>
     * Da alle Methoden statisch sind, wird dieser Konstruktor selten verwendet.
     * </p>
     */
    public BestandMapper(){}

    /**
     * Wandelt ein {@link BestandDTO} in ein {@link Bestand}-Entity um.
     *
     * @param dto BestandDTO
     * @return Bestand-Entity oder {@code null}, falls das DTO {@code null} ist
     */
    public static Bestand toEntity(BestandDTO dto, Hersteller hersteller) {
        if (dto == null) {
            return null;
        }

        Bestand bestand = new Bestand(
                dto.getLok() != null ? LokMapper.toEntity(dto.getLok()) : null,
                dto.getLagerplatz() != null ? LagerplatzMapper.toEntity(dto.getLagerplatz()) : null,
                dto.getMenge()
        );
        bestand.setId(dto.getId());
        bestand.setHersteller(hersteller);
        return bestand;


    }
}
