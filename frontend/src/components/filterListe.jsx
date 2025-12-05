import React from "react";
import { useState } from "react";
import { Filter } from "./filter";
import { fetchFilter, fetchSearchFilter } from "../data/api";
import { SearchField } from "./SearchField";
import { Button } from "./button";

export const FilterListe = () => {
  const [loks, setLoks] = useState([]);
  const [error, setError] = useState("");

  const [artNumber, setArtNumber] = useState("");
  const [hersteller, setHersteller] = useState("");
  const [bezeichnung, setBezeichnung] = useState("");

  const handleSearch = async (searchParams) => {
    try {
      const params = searchParams ?? {
        artNumber,
        modell,
        herstellerName: hersteller,
      };
      const data = await fetchSearchFilter(params);
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
      const data = await fetchFilter(filters);
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
