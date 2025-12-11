import React, { useState } from "react";
import { createLok, editLok } from "../services/lok-serivce";
import { Button } from "./button";

/**
 * LokErstellen Component
 * Formular zum Erstellen oder Bearbeiten einer Lok.
 *
 * @param {object} initialData - Optionale Initialwerte für die Lok
 * @param {function(object):void} [onSaved] - Callback, wenn Lok gespeichert wurde
 * @component
 * @returns {JSX.Element}
 */

export const LokErstellen = ({ initialData = {}, onSaved }) => {
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

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      let savedLok;
      if (formData.id) {
        savedLok = await editLok(formData.id, formData); // Bearbeiten/Duplizieren
      } else {
        savedLok = await createLok(formData); // Neue Lok
      }
      setMessage(`Lok ${savedLok.artNumber} erfolgreich gespeichert`);
      if (onSaved) onSaved(savedLok);
    } catch (err) {
      console.error(err);
      setMessage("Fehler beim Speichern");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="lok-form">
      <h2 className="lok-form-title">
        {formData.id ? "Lok bearbeiten / duplizieren" : "Neue Lok erstellen"}
      </h2>
      {message && <p className="lok-message">{message}</p>}
      {Object.entries(formData).map(
        ([key, value]) =>
          key !== "id" && (
            <div key={key} className="lok-form-group">
              <label className="lok-label">{labelMap[key]}</label>
              <input
                type="text"
                name={key}
                value={value}
                onChange={handleChange}
              />
            </div>
          )
      )}

      <Button
        className="lok-form-button"
        text={formData.id ? "Änderungen speichern" : "Lok hinzufügen"}
        onAnswerClick={handleSubmit}
      />
    </form>
  );
};
