import { useState } from "react";
import { register } from "../services/auth-service";
import { Link, useNavigate } from "react-router-dom";

/**
 * Registrierungs-Seite für neue Benutzer.
 *
 * Zeigt ein Formular mit Username, Email und Passwort.
 * Bei erfolgreicher Registrierung wird der Benutzer zum Login weitergeleitet.
 *
 * @component
 * @returns {JSX.Element} Register
 */
const Register = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  /**
   * Handler für das Registrierungsformular.
   * Sendet die Daten an das Backend und navigiert nach Erfolg zum Login.
   *
   * @param {React.FormEvent} e - Submit-Event
   */
  const handleRegister = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      await register({ username, email, password });
      alert("Registrierung erfolgreich! Bitte einloggen.");
      navigate("/login");
    } catch (err) {
      setError(err.message || "Registrierung fehlgeschlagen.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-container">
        <form onSubmit={handleRegister} className="auth-form">
          <h2>Registrieren</h2>

          {error && <div className="error-message">{error}</div>}

          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />

          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <input
            type="password"
            placeholder="Passwort"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          <button type="submit" disabled={loading}>
            {loading ? "Registrieren..." : "Registrieren"}
          </button>

          <div className="auth-links">
            <p>Schon einen Account?</p>
            <Link to="/login">Jetzt einloggen</Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;
