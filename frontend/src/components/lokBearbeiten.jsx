import React, { useCallback, useState } from "react";
import { SearchField } from "./SearchField";
import { LokDetails } from "./lokDetails";
import { Button } from "./button";
import { LokErstellen } from "./lokErstellen";
import { useEffect } from "react";

/**
 * LokBearbeiten Component
 * Ermöglicht Suchen, Anzeigen und Bearbeiten/Duplizieren von Loks
 *
 * @component
 * @returns {JSX.Element}
 */
export const LokBearbeiten = () => {
  const [artNumber, setArtNumber] = useState("");
  const [detailsOpen, setDetailsOpen] = useState(false);
  const [editOpen, setEditOpen] = useState(false);
  const [lokData, setLokData] = useState(null);

  useEffect(() => {
    setDetailsOpen(false);
    setEditOpen(false);
    setLokData(null);
  }, [artNumber]);

  const handleEdit = () => {
    setEditOpen(true);
  };

  const handleLoad = useCallback((hasData, data) => {
    setDetailsOpen(hasData);
    if (hasData) setLokData(data[0]?.lok || null);
  }, []);
  return (
    <div>
      <h2>Lok Bearbeiten/Duplizieren:</h2>
      <SearchField label="Artikel Nummer" onSearch={setArtNumber} />

      {!editOpen && (
        <LokDetails artNumber={artNumber} onLoad={handleLoad} isEdit={true} />
      )}

      {detailsOpen && !editOpen && (
        <Button text="Bearbeiten" onAnswerClick={handleEdit} />
      )}
      {editOpen && lokData && (
        <LokErstellen
          initialData={{
            ...lokData,
            artNumber: `${lokData.artNumber}_edit`,
            herstellerName: lokData.hersteller?.name || "",
          }}
        />
      )}
    </div>
  );
};
