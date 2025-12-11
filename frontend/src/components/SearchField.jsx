import React, { useState } from "react";
import { Button } from "./button";
import "../css/Style.css";

/**
 * SearchField Component
 * Eingabefeld zur Suche nach Artikeln oder Loks.
 *
 * @param {string} label - Label für das Inputfeld
 * @param {function(string):void} onSearch - Callback, wenn Suche ausgeführt wird
 * @component
 * @returns {JSX.Element}
 */
export const SearchField = ({ label, onSearch }) => {
  const [input, setInput] = useState("");

  const handleSearch = () => {
    if (input.trim() === "") return;
    onSearch(input.trim());
  };

  const handleKeyPress = (e) => {
    if (e.key === "Enter") handleSearch();
  };

  return (
    <div className="search-field">
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyDown={handleKeyPress}
        placeholder={`Hier ${label} eingeben`}
      />
      <Button text="Suche" onAnswerClick={handleSearch} />
    </div>
  );
};
