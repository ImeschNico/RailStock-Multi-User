import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";

const Navigation = () => {
  //AuthContext
  const { user, isAuthenticated } = useContext(AuthContext);

  return (
    <nav className="nav-bar-top">
      <Link to="/">Home</Link>
      <Link to="/suche">Suche</Link>
      <Link to="/lagerplatz">Lagerplatz</Link>
      <Link to="/login">Login</Link>
      <Link to="/admin">Admin</Link>

      {/* User-Anzeige */}
      {isAuthenticated && (
        <span
          style={{
            marginLeft: "20px",
            padding: "5px 10px",
            background: user.role === "ADMIN" ? "#dc3545" : "#007bff",
            color: "white",
            borderRadius: "4px",
            fontSize: "14px",
          }}
        >
          👤 {user.username} ({user.role})
        </span>
      )}
    </nav>
  );
};

export default Navigation;
