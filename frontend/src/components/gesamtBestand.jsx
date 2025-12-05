import React, { useEffect, useState } from "react";
import { fetchGesamtBestand } from "../data/api";

export const GesamtBestand = () => {
  const [gesamt, setGesamt] = useState(0);

  useEffect(() => {
    fetchGesamtBestand().then(setGesamt);
  }, []);

  return <div className="gesamt-bestand">Gesamter Bestand: {gesamt}</div>;
};
