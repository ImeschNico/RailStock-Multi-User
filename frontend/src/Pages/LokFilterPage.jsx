import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { getBestandByHersteller } from "../services/hersteller-service";

export const LokFilterPage = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const hersteller = queryParams.get("hersteller");

  const [loks, setLoks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [openArtNumber, setOpenArtNumber] = useState(null); // welche Lok aktuell geöffnet

  useEffect(() => {
    if (hersteller) {
      setLoading(true);
      getBestandByHersteller(hersteller)
        .then((res) => {
          setLoks(res);
          setLoading(false);
        })
        .catch((err) => {
          console.error(err);
          setError("Fehler beim Laden der Loks");
          setLoading(false);
        });
    }
  }, [hersteller]);

  if (!hersteller) return <div>Kein Hersteller ausgewählt</div>;
  if (loading) return <div>Lädt...</div>;
  if (error) return <div>{error}</div>;
  if (!loks.length) return <div>Keine Loks gefunden</div>;

  const toggleBestand = (artNumber) => {
    setOpenArtNumber(openArtNumber === artNumber ? null : artNumber);
  };

  const getGesamtMenge = (bestand) => {
    if (!bestand) return 0;
    return bestand.reduce((sum, b) => sum + b.menge, 0);
  };

  return (
    <div className="lfp-container">
      <h2 className="lfp-title">Loks von {hersteller}</h2>
      <div className="lfp-grid">
        {loks.map((lok) => (
          <div key={lok.id} className="lfp-card">
            <div
              className="lfp-card-header"
              onClick={() => toggleBestand(lok.artNumber)}
            >
              <span className="lfp-artNumber">{lok.artNumber}</span>
              <span className="lfp-bezeichnung">{lok.bezeichnung}</span>
              <span className="lfp-bestand">
                Bestand: {getGesamtMenge(lok.bestand)}
              </span>
              <span className="lfp-typ">({lok.typ})</span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
