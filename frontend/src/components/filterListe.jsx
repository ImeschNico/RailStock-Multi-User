import React from "react";
import { useState } from "react";
import { Filter } from "./filter";
import { filterLoks, searchLoks } from "../services/lok-serivce";
import { SearchField } from "./SearchField";
import { Button } from "./button";

/**
 * FilterListe Component
 * Anzeige einer Liste von Loks mit Such- und Filterfunktion
 *
 * @component
 * @returns {JSX.Element}
 */
export const FilterListe = () => {
  const [loks, setLoks] = useState([]);
  const [error, setError] = useState("");
  const [artNumber, setArtNumber] = useState("");
  const [modell, setModell] = useState("");
  const [hersteller, setHersteller] = useState("");
  const [bezeichnung, setBezeichnung] = useState("");

  const handleSearch = async (searchParams) => {
    try {
      const params = searchParams ?? {
        artNumber,
        modell,
        herstellerName: hersteller,
      };
      const data = await searchLoks(params);
      setLoks(data);
    } catch (err) {
      console.error(err);
      setError("Fehler beim Laden der Daten");
    }
  };

  const handleSearchFieldChange = (field) => (value) => {
    let params = {};

    switch (field) {
      case "hersteller":
        setHersteller(value);
        params.herstellerName = value;
        break;
      default:
        break;
    }
    handleSearch(params);
  };

  const handleFilterChange = async (filters) => {
    try {
      const data = await filterLoks(filters);
      setLoks(data);
    } catch (err) {
      console.error(err);
      setError("Fehler beim Laden der Daten");
    }
  };

  return (
    <div className="filter-section">
      {/*Freie TextSuche */}
      <div className="search-fields">
        <SearchField
          label="Hersteller"
          onSearch={handleSearchFieldChange("hersteller")}
        />
      </div>

      <Filter onFilterChange={handleFilterChange} />
      {error && <p>{error}</p>}

      <div className="lok-container">
        {loks.map((lok) => (
          <div className="lok-card" key={lok.id}>
            {lok.bezeichnung}
          </div>
        ))}
      </div>
    </div>
  );
};
