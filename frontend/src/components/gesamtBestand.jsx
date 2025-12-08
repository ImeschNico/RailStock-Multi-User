import React, { useEffect, useState } from "react";
import { getGesamtBestand } from "../services/bestand-service";

export const GesamtBestand = () => {
  const [gesamt, setGesamt] = useState(0);

  useEffect(() => {
    getGesamtBestand().then(setGesamt);
  }, []);

  return <div className="gesamt-bestand">Gesamter Bestand: {gesamt}</div>;
};
