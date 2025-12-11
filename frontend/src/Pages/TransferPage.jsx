import { useLocation } from "react-router-dom";
import { BestandListe } from "../components/bestandListe";

/**
 * Seite für den Transfer von Lok-Beständen.
 * Liest die ArtNumber aus den Query-Parametern aus und zeigt die BestandListe.
 *
 * URL-Beispiel: /transfer?artNumber=1234
 *
 * @component
 * @returns {JSX.Element} TransferPage
 */
export const TransferPage = () => {
  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const artNumber = query.get("artNumber");

  return (
    <div>
      <h2>Transfer für Lok {artNumber}</h2>
      <BestandListe artNumber={artNumber} />
    </div>
  );
};
