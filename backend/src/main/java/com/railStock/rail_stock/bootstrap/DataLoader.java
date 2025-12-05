package com.railStock.rail_stock.bootstrap;

import com.railStock.rail_stock.entity.Hersteller;
import com.railStock.rail_stock.entity.Lagerplatz;
import com.railStock.rail_stock.entity.Lok;
import com.railStock.rail_stock.repository.HerstellerRepository;
import com.railStock.rail_stock.repository.LagerplatzRepository;
import com.railStock.rail_stock.repository.LokRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Lädt initiale Daten für Hersteller und Loks aus CSV-Dateien in die Datenbank.
 * <p>
 * Wird beim Start der Spring Boot Anwendung ausgeführt.
 * </p>
 *
 * Autor: Nico Imesch
 * Version: 1.0
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final HerstellerRepository herstellerRepository;
    private final LokRepository lokRepository;
    private final LagerplatzRepository lagerplatzRepository; // ✅ Feld hinzufügen

    /**
     * Konstruktor für Dependency Injection.
     *
     * @param herstellerRepository Repository für Hersteller
     * @param lokRepository Repository für Lokomotiven
     */
    public DataLoader(HerstellerRepository herstellerRepository,
                      LokRepository lokRepository,
                      LagerplatzRepository lagerplatzRepository) {
        this.herstellerRepository = herstellerRepository;
        this.lokRepository = lokRepository;
        this.lagerplatzRepository = lagerplatzRepository; // ✅ injizieren
    }

    /**
     * Führt das Laden der CSV-Daten aus, falls die Tabellen leer sind.
     *
     * @param args Startargumente (werden nicht verwendet)
     * @throws Exception bei Fehlern beim Einlesen der CSV-Dateien
     */
    @Override
    public void run(String... args) throws Exception {
        // Hersteller laden
        if (herstellerRepository.count() == 0) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/hersteller.csv")
            ))) {
                String line;
                br.readLine(); // Kopfzeile überspringen
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 2) {
                        Hersteller hersteller = new Hersteller();
                        hersteller.setName(parts[1].trim());
                        herstellerRepository.save(hersteller);
                    }
                }
            }
        }

        // Loks laden
        if (lokRepository.count() == 0) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/lokDaten.csv")
            ))) {
                String line;
                br.readLine(); // Kopfzeile überspringen
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 9) {
                        Lok lok = new Lok();
                        lok.setArtNumber(parts[0].trim());
                        lok.setBezeichnung(parts[1].trim());
                        lok.setTyp(parts[2].trim());
                        lok.setModell(parts[3].trim());
                        lok.setStromart(parts[4].trim());
                        lok.setSpur(parts[5].trim());
                        lok.setEpoche(parts[6].trim());
                        lok.setBetriebsart(parts[7].trim());

                        String herstellerName = parts[8].trim();
                        Hersteller hersteller = herstellerRepository.findByName(herstellerName);
                        if (hersteller == null) {
                            hersteller = new Hersteller();
                            hersteller.setName(herstellerName);
                            hersteller = herstellerRepository.save(hersteller);
                        }
                        lok.setHersteller(hersteller);
                        lokRepository.save(lok);
                    }
                }
            }
        }

        //  LEGACY-Lagerplatz anlegen, außerhalb der Schleifen
        if (lagerplatzRepository.count() == 0) {
            Lagerplatz legacyLp = new Lagerplatz();
            legacyLp.setRegal("LEGACY");
            legacyLp.setTablar("LEGACY");
            lagerplatzRepository.save(legacyLp);
        }
    }
}