import { useLocation } from "react-router-dom";
import { BestandListe } from "../components/bestandListe";

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
