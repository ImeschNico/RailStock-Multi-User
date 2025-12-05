import React, { useState } from "react";
import { createLagerplatz } from "../data/api";
import { Button } from "./button";
import { Lagerplatz } from "../Pages/Lagerplatz";

export const Erstellen = () => {
  const [regal, setRegal] = useState("");
  const [tablar, setTablar] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError(null);

    try {
      const data = await createLagerplatz(regal, tablar);
      setMessage(`Lagerplatz ${regal}-${tablar} erfolgreich erstellt`);
      setRegal("");
      setTablar("");
    } catch (err) {
      setError("Fehler beim erstellen des Lagerplatzes");
    }
  };

  return (
    <div className="suche-container erstellen-container">
      <h2>Lagerplatz erstellen</h2>

      <form onSubmit={handleSubmit} className="erstellen-form">
        <div className="search-fields">
          <div className="search-field">
            <input
              type="text"
              value={regal}
              onChange={(e) => setRegal(e.target.value.toUpperCase())}
              placeholder="Regal (z. B. S01)"
              required
            />
          </div>

          <div className="search-field">
            <input
              type="text"
              value={tablar}
              onChange={(e) => setTablar(e.target.value.toUpperCase())}
              placeholder="Tablar (z. B. A01)"
              required
            />
          </div>

          <div className="search-field">
            <button type="submit">Erstellen</button>
          </div>
        </div>
      </form>

      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
    </div>
  );
};
