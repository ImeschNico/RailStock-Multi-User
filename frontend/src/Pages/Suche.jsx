import React, { useEffect, useState } from "react";
import { SearchField } from "../components/SearchField";
import "../css/Style.css";
import { FilterListe } from "../components/filterListe";
import { BestandListe } from "../components/bestandListe";
import { Hersteller } from "../components/hersteller";
import { useLocation, useNavigate } from "react-router-dom";
import { Filter } from "../components/filter";

export const Suche = () => {
  const navigate = useNavigate();

  const handleHerstellerSelected = (name) => {
    navigate(`/loks/filter?hersteller=${encodeURI(name)}`);
  };

  return (
    <div className="suche-container">
      <h2>Wähle einen Hersteller aus:</h2>

      <Hersteller onSelectedHersteller={handleHerstellerSelected} />
    </div>
  );
};
