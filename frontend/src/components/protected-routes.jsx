import { useAuth } from "../contexts/AuthContext";
import { Navigate } from "react-router-dom";

/**
 * ProtectedRoute Component
 * Schützt Routen vor unauthentifizierten Zugriffen
 *
 * @param {ReactNode} children - Die zu schützende Component
 * @param {string} requiredRole - Optional: Erforderliche Rolle (z.B. "ADMIN")
 */
const ProtectedRoute = ({ children, requiredRole }) => {
  const { isAuthenticated, user, isLoading } = useAuth();

  // Während AuthContext lädt
  if (isLoading) {
    return (
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          minHeight: "60vh",
        }}
      >
        <h2>Lädt...</h2>
      </div>
    );
  }

  // Check 1: Ist User eingeloggt?
  if (!isAuthenticated) {
    console.log("ProtectedRoute: Nicht eingeloggt");
    return (
      <div style={{ textAlign: "center", marginTop: "50px" }}>
        <h2>🚫 Protected: Du bist nicht eingeloggt!</h2>
        <p>
          Bitte <a href="/login">einloggen</a>, um auf diese Seite zuzugreifen.
        </p>
      </div>
    );
  }

  // Check 2: Hat User die richtige Rolle? (optional)
  if (requiredRole && user?.role !== requiredRole) {
    console.log(
      `ProtectedRoute: Rolle "${user.role}" nicht ausreichend. Erforderlich: "${requiredRole}"`
    );
    return <Navigate to="/forbidden" replace />;
  }

  // Alle Checks bestanden ✅
  console.log("ProtectedRoute: Zugriff gewährt");
  return children;
};

export default ProtectedRoute;
