import React from "react";
import { useState, useEffect } from "react";
import { getBestandByArtNumber } from "../services/bestand-service";

export const LokDetails = ({ artNumber, onLoad, isEdit = false }) => {
  const [bestand, setBestand] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!artNumber) return;

    const fetchBestand = async () => {
      setLoading(true);
      try {
        const data = await getBestandByArtNumber(artNumber);
        setBestand(data);
        if (onLoad) onLoad(data.length > 0, data);
      } catch (err) {
        console.error(err);
        setBestand([]);
        if (onLoad) onLoad(false);
      } finally {
        setLoading(false);
      }
    };

    fetchBestand();
  }, [artNumber, onLoad]);

  if (loading) return <p>Lade...</p>;
  if (!artNumber) return <p>Bitte Art Nr eingeben</p>;
  if (!bestand || bestand.length === 0) return <p>Keine Lok gefunden</p>;

  const lokData = bestand[0].lok;
  return (
    <div className={`lok-container ${isEdit ? "edit" : ""}`}>
      <div className="lok-card">
        <h3>
          <strong>Hersteller:</strong> {lokData.herstellerName}
        </h3>
        <p>
          <strong>Art. Nr:</strong> {lokData.artNumber}
        </p>
        <p>
          <strong>Bezeichnung:</strong> {lokData.bezeichnung}
        </p>
        <p>
          <strong>Typ:</strong> {lokData.typ}
        </p>
        <p>
          <strong>Modell:</strong> {lokData.modell}
        </p>
        <p>
          <strong>Stromart:</strong> {lokData.stromart}
        </p>
        <p>
          <strong>Spur:</strong> {lokData.spur}
        </p>
        <p>
          <strong>Epoche:</strong> {lokData.epoche}
        </p>
        <p>
          <strong>Betriebsart:</strong> {lokData.betriebsart}
        </p>
      </div>
    </div>
  );
};
