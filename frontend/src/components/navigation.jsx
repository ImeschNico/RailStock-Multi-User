import React from "react";
import { Link } from "react-router-dom";

export const Navigation = () => {
  return (
    <nav className="nav-bar-top">
      <Link to="/">Home</Link>
      <Link to="/suche">Suche</Link>
      <Link to="/lagerplatz">Lagerplatz</Link>
      <Link to="/admin">Admin</Link>
    </nav>
  );
};
