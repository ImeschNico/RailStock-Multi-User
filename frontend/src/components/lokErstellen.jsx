import React, { useState } from "react";
import { createLok } from "../services/lok-serivce";
import { Button } from "./button";

export const LokErstellen = ({ initialData = {} }) => {
  const [message, setMessage] = useState("");
  const [formData, setFormData] = useState({
    artNumber: initialData.artNumber || "",
    bezeichnung: initialData.bezeichnung || "",
    typ: initialData.typ || "",
    modell: initialData.modell || "",
    stromart: initialData.stromart || "",
    spur: initialData.spur || "",
    epoche: initialData.epoche || "",
    betriebsart: initialData.betriebsart || "",
    herstellerName: initialData.herstellerName || "",
  });

  const labelMap = {
    artNumber: "Artikelnummer",
    bezeichnung: "Bezeichnung",
    typ: "Typ",
    modell: "Modell",
    stromart: "Stromart",
    spur: "Spur",
    epoche: "Epoche",
    betriebsart: "Betriebsart",
    herstellerName: "Hersteller",
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Gesendete Daten:", formData);
    createLok(formData);
    setMessage(`Lok ${formData.artNumber} erfolgreich erstellt`);
  };

  return (
    <form onSubmit={handleSubmit} className="lok-form">
      <h2 className="lok-form-title">Neue Lok erstellen:</h2>
      {message && <p className="lok-message">{message}</p>}
      {Object.entries(formData).map(([key, value]) => (
        <div key={key} className="lok-form-group">
          <label className="lok-label">{labelMap[key]}</label>
          <input type="text" name={key} value={value} onChange={handleChange} />
        </div>
      ))}

      <Button
        className="lok-form-button"
        text={"Lok hinzufügen"}
        onAnswerClick={handleSubmit}
      />
    </form>
  );
};
