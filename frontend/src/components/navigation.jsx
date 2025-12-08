import { Link } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { Button } from "./button";

const Navigation = () => {
  //AuthContext
  const { user, isAuthenticated, logout } = useAuth();

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
          <Button
            onClick={logout}
            style={{ padding: "5px 10px", cursor: "pointer" }}
          >
            Logout
          </Button>
        </span>
      )}
    </nav>
  );
};

export default Navigation;
