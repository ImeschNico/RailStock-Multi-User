import "../css/Style.css";
import { useNavigate } from "react-router-dom";
import LoginForm from "../components/login-form";
import { useState } from "react";
import { useAuth } from "../contexts/AuthContext";

/**
 * Login-Seite für bestehende Benutzer.
 * Zeigt das Login-Formular an und navigiert bei erfolgreichem Login zur Home-Seite.
 *
 * @component
 * @returns {JSX.Element} Login
 */
const Login = () => {
  const navigate = useNavigate();
  const [error, setError] = useState("");
  const { login } = useAuth();

  /**
   * Handler für das Login-Formular.
   * Ruft den login aus AuthContext auf und navigiert bei Erfolg.
   *
   * @param {{ usernameOrEmail: string, password: string }} loginData - Login-Daten
   */
  const handleLogin = async (loginData) => {
    setError("");

    try {
      await login(loginData.usernameOrEmail, loginData.password);
      navigate("/home");
    } catch (err) {
      setError(err.message || "Login fehlgeschlagen.");

      setTimeout(() => setError(""), 10000);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-container">
        {error ? <div className="error-message">❌ {error}</div> : null}

        <LoginForm onLogin={handleLogin} />

        <div className="auth-links">
          <p>Noch kein Account?</p>
          <p>
            <a href="/register" className="register">
              Jetzt Registrieren
            </a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;
