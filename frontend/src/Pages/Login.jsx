import "../css/Style.css";
import { useNavigate } from "react-router-dom";
import LoginForm from "../components/login-form";
import { useState } from "react";
import { useAuth } from "../contexts/AuthContext";

const Login = () => {
  const navigate = useNavigate();
  const [error, setError] = useState("");
  const { login } = useAuth();

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
