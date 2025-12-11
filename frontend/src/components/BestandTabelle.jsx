import React, { useState } from "react";
import { Button } from "./button";
import { Link } from "react-router-dom";
import {
  getBestandByArtNumber,
  updateBestand,
} from "../services/bestand-service";
import { transferBestand } from "../services/bestand-service";
import "../css/Style.css";

/**
 * BestandTabelle Component
 * Zeigt den Bestand einer Lok in einer Tabelle an.
 * Ermöglicht Bestand-Eingang oder Transfer zwischen Lagerplätzen.
 *
 * @param {Array} bestand - Aktuelle Bestand-Daten
 * @param {function} setBestand - Setter-Funktion für Bestand
 * @param {number} lokId - ID der Lok
 * @component
 * @returns {JSX.Element}
 */
export const BestandTabelle = ({ bestand, setBestand, lokId }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentItem, setCurrentItem] = useState(null);
  const [newMenge, setNewMenge] = useState("");
  const [modalType, setModalType] = useState(null);
  const [zuRegal, setZuRegal] = useState("");
  const [zuTablar, setZuTablar] = useState("");

  const ModalOpen = (item, type) => {
    setCurrentItem(item);
    setNewMenge(item.menge);
    setModalType(type);
    setIsModalOpen(true);
  };

  const saveChanges = async () => {
    if (modalType === "eingang") {
      try {
        const updatedItem = await updateBestand({
          artNumber: currentItem.lok.artNumber,
          regal: currentItem.lagerplatz.regal,
          tablar: currentItem.lagerplatz.tablar,
          menge: Number(newMenge),
        });

        const refreshed = await getBestandByArtNumber(
          currentItem.lok.artNumber
        );
        setBestand(refreshed);

        setIsModalOpen(false);
      } catch (error) {
        console.error(error);
      }
    }
    if (modalType === "transferieren") {
      try {
        const updatedItem = await transferBestand({
          artNumber: currentItem.lok.artNumber,
          vonRegal: currentItem.lagerplatz.regal,
          vonTablar: currentItem.lagerplatz.tablar,
          zuRegal: zuRegal,
          zuTablar: zuTablar,
          menge: Number(newMenge),
        });

        const refreshed = await getBestandByArtNumber(
          currentItem.lok.artNumber
        );
        setBestand(refreshed);

        setIsModalOpen(false);
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <>
      <table className="bestand-tabelle">
        <thead>
          <tr>
            <th>Regal</th>
            <th>Tablar</th>
            <th>Menge</th>
            <th>Aktion</th>
          </tr>
        </thead>
        <tbody>
          {bestand.map((b) => (
            <tr key={b.id}>
              <td>{b.lagerplatz.regal}</td>
              <td>{b.lagerplatz.tablar}</td>
              <td>{b.menge}</td>
              <td>
                <Button
                  text="Bestand Eingang"
                  onAnswerClick={() => ModalOpen(b, "eingang")}
                />
                <Button
                  text="Transferieren"
                  onAnswerClick={() => {
                    ModalOpen(b, "transferieren");
                  }}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {isModalOpen && modalType === "eingang" && (
        <div className="modal-backdrop">
          <div className="modal">
            <h2>Bestand Eingang</h2>
            <p>
              Lagerplatz: {currentItem.lagerplatz.regal} -{" "}
              {currentItem.lagerplatz.tablar}
            </p>
            <input
              type="number"
              value={newMenge}
              onChange={(e) => setNewMenge(e.target.value)}
            />
            <div className="modal-buttons">
              <Button
                text="Speichern"
                onAnswerClick={saveChanges}
                className={"button-eingang"}
              />
              <Button
                text="Abbrechen"
                onAnswerClick={() => setIsModalOpen(false)}
              />
            </div>
          </div>
        </div>
      )}

      {isModalOpen && modalType === "transferieren" && (
        <div className="modal-backdrop">
          <div className="modal">
            <h2>Bestand transferieren</h2>
            <p>
              Von: {currentItem.lagerplatz.regal} /{" "}
              {currentItem.lagerplatz.tablar}
            </p>
            <input
              type="text"
              placeholder="Ziel-Regal"
              value={zuRegal}
              onChange={(e) => setZuRegal(e.target.value.toUpperCase())}
            />
            <input
              type="text"
              placeholder="Ziel-Tablar"
              value={zuTablar}
              onChange={(e) => setZuTablar(e.target.value.toUpperCase())}
            />
            <input
              type="number"
              value={newMenge}
              onChange={(e) => setNewMenge(e.target.value)}
            />
            <div className="modul-buttons">
              <Button
                text="Speichern"
                onAnswerClick={saveChanges}
                className={"button-transfer"}
              />
              <Button
                text="Abbrechen"
                onAnswerClick={() => setIsModalOpen(false)}
              />
            </div>
          </div>
        </div>
      )}
    </>
  );
};
