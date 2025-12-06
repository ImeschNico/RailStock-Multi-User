// import React, { useState } from "react";
// import { SearchField } from "../components/SearchField";
// import { BestandListe } from "../components/bestandListe";
// import { GesamtBestand } from "../components/gesamtBestand";

import AuthTest from "../components/auth-test";

export const Home = () => {
  //const [artNumber, setArtNumber] = useState("");

  return (
    <div>
      <p>Teste hier den AuthContext:</p>
      {/* Test Component */}
      <AuthTest />
    </div>

    // <div>
    //   <GesamtBestand />
    //   <h2 className="start-titel">
    //     Willkommen
    //     <p>Zur Suche eines Artikels ArtNr eingeben:</p>
    //   </h2>
    //   <SearchField label="Artikel Nummer" onSearch={setArtNumber} />
    //   <BestandListe artNumber={artNumber} />
    // </div>
  );
};
