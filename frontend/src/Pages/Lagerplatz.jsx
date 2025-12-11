import React from "react";
import { Erstellen } from "../components/erstellen";

/**
 * Seite für Lagerplatz-Verwaltung.
 * Zeigt vorhandene Lagerplätze an und ermöglicht das Erstellen neuer Plätze.
 *
 * @component
 * @returns {JSX.Element} Lagerplatz
 */
export const Lagerplatz = () => {
  return (
    <div className="lagerplatz-container">
      <h2>Lagerplätze</h2>
      <Erstellen />
    </div>
  );
};
