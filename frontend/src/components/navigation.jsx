import { Link } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";

const Navigation = () => {
  const { user, isAuthenticated, logout } = useAuth();

  const handleLogout = () => {
    console.log("🚪 Logout-Button geklickt");
    logout();
  };

  return (
    <nav className="nav-bar-top">
      {/* Öffentliche Links */}
      <Link to="/">Home</Link>

      {/* Geschützte Links (nur wenn eingeloggt) */}
      {isAuthenticated && (
        <>
          <Link to="/suche">Suche</Link>
          <Link to="/lagerplatz">Lagerplatz</Link>

          {/* Admin-Link */}
          {user?.role === "ADMIN" && <Link to="/admin">Admin</Link>}
        </>
      )}

      {/* Rechts: Login / Logout */}
      <div
        style={{
          marginLeft: "auto",
          display: "flex",
          gap: "10px",
          alignItems: "center",
        }}
      >
        {isAuthenticated ? (
          <>
            <span
              style={{
                color: "#28a745",
                fontWeight: "bold",
                padding: "5px 10px",
                backgroundColor: "rgba(40, 167, 69, 0.1)",
                borderRadius: "4px",
              }}
            >
              👤 {user?.username || "User"}
            </span>

            <button
              onClick={handleLogout}
              style={{
                padding: "8px 16px",
                backgroundColor: "#dc3545",
                color: "white",
                border: "none",
                borderRadius: "4px",
                cursor: "pointer",
                fontWeight: "bold",
              }}
            >
              Logout
            </button>
          </>
        ) : (
          // Login anzeigen, WENN NICHT eingeloggt
          <Link to="/login">Login</Link>
        )}
      </div>
    </nav>
  );
};

export default Navigation;
