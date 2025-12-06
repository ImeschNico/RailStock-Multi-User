import React, { useEffect, useState } from "react";
import { getHersteller } from "../services/hersteller-service";
import maerklinLogo from "../assets/bild_maerklin.png";
import bemoLogo from "../assets/bild_bemo.png";
import hagLogo from "../assets/bild_hag.png";
import lemacoLogo from "../assets/bild_lemaco.png";
import metropolitanLogo from "../assets/bild_metropolitan.png";
import rivarossiLogo from "../assets/bild_rivarossi.png";
import { Button } from "./button";
import { Filter } from "./filter";

export const Hersteller = ({ onSelectedHersteller }) => {
  const [hersteller, setHersteller] = useState([]);

  const herstellerLogos = {
    Märklin: maerklinLogo,
    Bemo: bemoLogo,
    Hag: hagLogo,
    Lamaco: lemacoLogo,
    Metropolitan: metropolitanLogo,
    Rivarossi: rivarossiLogo,
  };

  useEffect(() => {
    const loadHersteller = async () => {
      try {
        const data = await getHersteller();
        setHersteller(data);
      } catch (error) {
        console.error("Fehler beim Laden der Hersteller", error);
      }
    };
    loadHersteller();
  }, []);

  const handleHerstellerClick = (herstellerName) => {
    if (onSelectedHersteller) {
      onSelectedHersteller(herstellerName);
    }
  };

  return (
    <div className="hersteller-container">
      <div className="hersteller-grid">
        {hersteller.map((h) => (
          <Button
            key={h.id}
            className="hersteller-card"
            onAnswerClick={() => handleHerstellerClick(h.name)}
          >
            <img src={herstellerLogos[h.name]} alt={h.name} />
            <span>{h.name}</span>
          </Button>
        ))}
      </div>
    </div>
  );
};
