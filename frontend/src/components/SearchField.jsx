import React, { useState } from "react";
import { Button } from "./button";
import "../css/Style.css";

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
