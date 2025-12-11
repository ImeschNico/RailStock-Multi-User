import React, { useState } from "react";
import { Dropdown } from "./dropdown";
import "../css/Style.css";
import { Button } from "./button";

/**
 * Filter Component
 * Stellt Dropdown-Felder für Epoche, Spur, Typ etc. zur Verfügung
 *
 * @param {function(Object):void} onFilterChange - Callback wenn Filter angewendet wird
 * @component
 * @returns {JSX.Element}
 */
export const Filter = ({ onFilterChange }) => {
  const [epoche, setEpoche] = useState("");
  const [spur, setSpur] = useState("");
  const [betriebsart, setBetriebsart] = useState("");
  const [typ, setTyp] = useState("");
  const [stromart, setStromart] = useState("");

  const apllyFilter = () => {
    onFilterChange({ epoche, spur, betriebsart, typ, stromart });
  };

  return (
    <div className="filter-container">
      <Dropdown
        label="Epoche"
        value={epoche}
        onChange={setEpoche}
        options={["I", "II", "III", "IV", "V", "VI"]}
        className={epoche ? "active-filter" : ""}
      />
      <Dropdown
        label="Spur"
        value={spur}
        onChange={setSpur}
        options={["Z", "N", "TT", "H0", "0"]}
        className={spur ? "active-filter" : ""}
      />
      <Dropdown
        label="Betriebsart"
        value={betriebsart}
        onChange={setBetriebsart}
        options={["Digital", "Analog", "MFX"]}
        className={betriebsart ? "active-filter" : ""}
      />
      <Dropdown
        label="Typ"
        value={typ}
        onChange={setTyp}
        options={["Dampf", "Diesel", "Elektro"]}
        className={typ ? "active-filter" : ""}
      />
      <Dropdown
        label="Stromart"
        value={stromart}
        onChange={setStromart}
        options={["Gleichstrom", "Wechselstrom"]}
        className={stromart ? "active-filter" : ""}
      />

      <Button
        className="apply-filter-button"
        text="Filter anwenden"
        onAnswerClick={apllyFilter}
      />
    </div>
  );
};
