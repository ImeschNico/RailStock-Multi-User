import React from "react";
import { useParams } from "react-router-dom";
import { getBestandByArtNumber } from "../services/bestand-service";
import { getBestandByHersteller } from "../services/hersteller-service";
import { useState, useEffect } from "react";
import { Button } from "./button";
import { BestandTabelle } from "./BestandTabelle";

/**
 * HerstellerBestand Component
 * Zeigt Bestand eines Herstellers an und erlaubt Bearbeitung einzelner Loks
 *
 * @component
 * @returns {JSX.Element}
 */
export const HerstellerBestand = () => {
  const { herstellerName } = useParams();
  const [bestand, setBestand] = useState([]);
  const [loading, setLoading] = useState(false);
  const [lokBestand, setLokBestand] = useState([]);
  const [selectedLok, setSelectedLok] = useState(null);

  useEffect(() => {
    const fetchBestand = async () => {
      if (!herstellerName) return;
      setLoading(true);
      try {
        const data = await getBestandByHersteller(herstellerName);
        setBestand(data);
      } catch (err) {
        console.error(err);
        setBestand([]);
      } finally {
        setLoading(false);
      }
    };

    fetchBestand();
  }, [herstellerName]);

  if (loading) return <p>Lade...</p>;
  if (!bestand || bestand.length === 0) return <p>Keine Lok gefunden</p>;

  const handleBearbeiten = async (lok) => {
    try {
      const lokBestand = await getBestandByArtNumber(lok.artNumber);
      setLokBestand(lokBestand);
      setSelectedLok(lok);
    } catch (error) {
      console.log(error);
    }
  };

  const handleZurueck = () => {
    setSelectedLok(null);
    setLokBestand([]);
  };

  if (selectedLok) {
    return (
      <div>
        <h2>
          Bestand bearbeiten: {selectedLok.modell} ({selectedLok.artNumber})
        </h2>
        <Button text="Zurück zur Übersicht" onAnswerClick={handleZurueck} />
        <BestandTabelle
          bestand={lokBestand}
          setBestand={setLokBestand}
          lokId={selectedLok.id}
        />
      </div>
    );
  }

  return (
    <div>
      <h2>Bestand von: {herstellerName}</h2>
      <div className="lok-container">
        {bestand.map((b) => (
          <div key={b.id} className="lok-card">
            <p>
              <strong>Art. Nr: </strong>
              {b.lok.artNumber}
            </p>
            <p>
              <strong>Modell: </strong>
              {b.lok.modell}
            </p>
            <p>
              <strong>Betriebsart: </strong>
              {b.lok.betriebsart}
            </p>
            <p>
              <strong>Bestand: </strong>
              {b.menge}
            </p>
            <p>
              <strong>Lagerpaltz: </strong>
              {b.lagerplatz
                ? `${b.lagerplatz.regal} / ${b.lagerplatz.tablar}`
                : "Nicht zugeordnet"}
            </p>
            <Button
              text="Bearbeiten"
              onAnswerClick={() => handleBearbeiten(b.lok)}
            />
          </div>
        ))}
      </div>
    </div>
  );
};
