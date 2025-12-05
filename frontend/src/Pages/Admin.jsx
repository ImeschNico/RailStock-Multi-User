import React, { useState } from "react";
import { Button } from "../components/button";
import { LokErstellen } from "../components/lokErstellen";
import { LokBearbeiten } from "../components/lokBearbeiten";

export const Admin = () => {
  const [showForm, setShowForm] = useState(false);
  const [showEditFrom, setShowEditForm] = useState(false);

  return (
    <div>
      <h2>Admin</h2>
      <Button
        text={"Neue Lok anlegen"}
        onAnswerClick={() => setShowForm(true) & setShowEditForm(false)}
      />
      <Button
        text={"Bestehende Lok anpassen"}
        onAnswerClick={() => setShowEditForm(true) & setShowForm(false)}
      />

      {showForm && <LokErstellen />}
      {showEditFrom && <LokBearbeiten />}
    </div>
  );
};
