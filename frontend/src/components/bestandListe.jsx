import React, { useEffect, useState } from "react";
import { getBestandByArtNumber } from "../services/bestand-service";
import { BestandTabelle } from "./BestandTabelle";
import { LokDetails } from "./lokDetails";

export const BestandListe = ({ artNumber }) => {
  const [bestand, setBestand] = useState([]);
  const [loading, setLoading] = useState(false);
  const lokData = bestand && bestand.length > 0 ? bestand[0].lok : null;

  useEffect(() => {
    if (!artNumber) return;

    const fetchBestand = async () => {
      setLoading(true);
      try {
        const data = await getBestandByArtNumber(artNumber);
        setBestand(data);
      } catch (err) {
        console.error(err);
        setBestand([]);
      } finally {
        setLoading(false);
      }
    };

    fetchBestand();
  }, [artNumber]);

  if (loading) return <p>Lade...</p>;
  if (!artNumber) return <p>Bitte Art Nr eingeben</p>;
  if (!bestand || bestand.length === 0) return <p>Keine Lok gefunden</p>;
  return (
    <div className="lok-bestand-container">
      <LokDetails artNumber={artNumber} />

      <div className="bestand-card">
        <BestandTabelle
          bestand={bestand}
          setBestand={setBestand}
          lokId={lokData ? lokData.lokId : 0}
        />
      </div>
    </div>
  );
};
