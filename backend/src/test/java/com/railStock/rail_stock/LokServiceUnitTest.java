package com.railStock.rail_stock;

import com.railStock.rail_stock.dto.LokDTO;
import com.railStock.rail_stock.dto.LokFormDTO;
import com.railStock.rail_stock.entity.Hersteller;
import com.railStock.rail_stock.entity.Lok;
import com.railStock.rail_stock.repository.HerstellerRepository;
import com.railStock.rail_stock.repository.LokRepository;
import com.railStock.rail_stock.service.LokService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LokServiceUnitTest {

    private LokRepository lokRepository;
    private HerstellerRepository herstellerRepository;
    private LokService lokService;

    @BeforeEach
    void setUp() {
        lokRepository = mock(LokRepository.class);
        herstellerRepository = mock(HerstellerRepository.class);
        lokService = new LokService(lokRepository, herstellerRepository);
    }

    @Test
    void testCreateLok() {
        LokFormDTO dto = new LokFormDTO();
        dto.setBezeichnung("BR 101");
        dto.setHerstellerName("Märklin");

        Hersteller hersteller = new Hersteller();
        hersteller.setId(1L);
        hersteller.setName("Märklin");

        when(herstellerRepository.findByName("Märklin"))
                .thenReturn(hersteller);

        Lok savedLok = new Lok();
        savedLok.setId(1L);
        savedLok.setBezeichnung("BR 101");
        savedLok.setHersteller(hersteller);

        when(lokRepository.save(any(Lok.class))).thenReturn(savedLok);

        LokDTO result = lokService.createLok(dto);

        assertNotNull(result);
        assertEquals("BR 101", result.getBezeichnung());
        assertEquals("Märklin", result.getHerstellerName());

        verify(lokRepository, times(1)).save(any(Lok.class));
    }
}
