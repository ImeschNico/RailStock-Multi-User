import { useState } from "react";
import { register } from "../services/auth-service";
import { Link, useNavigate } from "react-router-dom";

const Register = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

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
