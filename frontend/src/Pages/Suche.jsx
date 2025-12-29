import "../css/Style.css";

import { Hersteller } from "../components/hersteller";
import { useNavigate } from "react-router-dom";

/**
 * Seite zur Auswahl eines Herstellers für die Lok-Suche.
 * Nach Auswahl eines Herstellers wird zur gefilterten Lok-Übersicht navigiert.
 *
 * @component
 * @returns {JSX.Element} Suche
 */
export const Suche = () => {
  const navigate = useNavigate();

  /**
   * Wird aufgerufen, wenn ein Hersteller ausgewählt wird.
   * Navigiert zur Lok-Filterseite mit dem ausgewählten Hersteller.
   *
   * @param {string} name - Name des ausgewählten Herstellers
   */
  const handleHerstellerSelected = (name) => {
    navigate(`/loks/filter?herstellerName=${encodeURIComponent(name)}`);
  };

  return (
    <div className="suche-container">
      <h2>Wähle einen Hersteller aus:</h2>

      <Hersteller onSelectedHersteller={handleHerstellerSelected} />
    </div>
  );
};
