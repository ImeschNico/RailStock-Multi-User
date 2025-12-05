import React from "react";
import { useLocation } from "react-router-dom";
import { fetchBestandByArtNumber, fetchBestandByHersteller } from "../data/api";
import { useState, useEffect } from "react";
import { Button } from "./button";
import { BestandTabelle } from "./BestandTabelle";

export const HerstellerBestand = () => {
  const { search } = useLocation();
  const params = new URLSearchParams(search);
  const hersteller = params.get("hersteller");
  const [bestand, setBestand] = useState([]);
  const [loading, setLoading] = useState(false);
  const [Bearbeiten, setBearbeiten] = useState(false);
  const [lokBestand, setLokBestand] = useState([]);
  const [selectedLok, setSelectedLok] = useState(null);

  useEffect(() => {
    if (!hersteller) return;

    const fetchBestand = async (herstellerName) => {
      setLoading(true);
      try {
        const data = await fetchBestandByHersteller(herstellerName);
        setBestand(data);
      } catch (err) {
        console.error(err);
        setBestand([]);
      } finally {
        setLoading(false);
      }
    };

    fetchBestand(hersteller);
  }, [hersteller]);

  if (loading) return <p>Lade...</p>;
  if (!bestand || bestand.length === 0) return <p>Keine Lok gefunden</p>;

  const lokData = bestand[0].lok;

  const handleBearbeiten = async (lok) => {
    try {
      const lokBestand = await fetchBestandByArtNumber(lok.artNumber);
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
      <h2>Bestand von: {hersteller}</h2>
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
