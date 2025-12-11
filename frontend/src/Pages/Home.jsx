import React, { useState } from "react";
import { SearchField } from "../components/SearchField";
import { BestandListe } from "../components/bestandListe";
import { GesamtBestand } from "../components/gesamtBestand";

/**
 * Startseite / Dashboard für angemeldete Benutzer.
 * Zeigt Gesamtbestand, Suchfeld und gefilterte Loks an.
 *
 * @component
 * @returns {JSX.Element} Home
 */
export const Home = () => {
  const [artNumber, setArtNumber] = useState("");

  return (
    <div>
      <GesamtBestand />
      <h2 className="start-titel">
        Willkommen
        <p>Zur Suche eines Artikels ArtNr eingeben:</p>
      </h2>
      <SearchField label="Artikel Nummer" onSearch={setArtNumber} />
      <BestandListe artNumber={artNumber} />
    </div>
  );
};
