import { createContext, useEffect, useState, useContext } from "react";
import {
  login as apiLogin,
  logout as apiLogout,
  getUserData,
} from "../services/auth-service";

//export erstellen
const AuthContext = createContext();

//Provider Context
export const AuthProvider = ({ children }) => {
  // ==========================================
  // STATE
  // ==========================================
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    checkAuth();
  }, []);

  // ==========================================
  // FUNKTIONEN
  // ==========================================
  /**
   * Prüft ob User eingeloggt ist (Token + User-Daten in localStorage?)
   */
  const checkAuth = () => {
    console.log("prüfe Auth Status...");

    const storedToken = localStorage.getItem("authToken");
    const storedUserData = getUserData();

    if (storedToken && storedUserData) {
      console.log("Token + USer Daten gefunden- User eingleoggt");
      setToken(storedToken);
      setUser(storedUserData);
      setIsAuthenticated(true);
    } else {
      console.log("Kein Token oder USer Data gefudnen - User nicht eingeloggt");
      setIsAuthenticated(false);
    }
    setIsLoading(false);
  };

  /**
   * Login Funktion (aktuell noch Fake)
   * Wird in Block 4A durch echten API Call ersetzt!
   *
   * @param {string} usernameOrEmail - Username ODER Email
   * @param {string} password - Passwort
   */
  const login = async (usernameOrEmail, password) => {
    try {
      console.log("AuthContext: Loging für: ", usernameOrEmail);

      //API Call
      const response = await apiLogin(usernameOrEmail, password);

      //Response enthält token userId username email role expires in
      setToken(response.token);
      setUser({
        id: response.userId,
        username: response.username,
        email: response.email,
        role: response.role,
      });
      setIsAuthenticated(true);

      console.log("AuthContext Login erfolgreich");
      return response;
    } catch (error) {
      console.error("AuthContext Login fehlgeschlagen");
      throw error;
    }
  };

  /**
   * Logout Funktion
   */
  const logout = () => {
    console.log("AuthContext Logout");
    apiLogout();
    setToken(null);
    setUser(null);
    setIsAuthenticated(false);
    window.location.href = "/";
  };

  // ==========================================
  // CONTEXT VALUE
  // ==========================================
  const value = {
    //state
    user,
    token,
    isLoading,
    isAuthenticated,
    //Funktionen
    login,
    logout,
    checkAuth,
  };

  // ==========================================
  // PROVIDER
  // ==========================================
  // Loading State während checkAuth läuft
  if (isLoading) {
    return (
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh",
        }}
      >
        <h2>Lädt...</h2>
      </div>
    );
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error(
      "useAuth muss innerhalb von AuthProvider verwendet werden!"
    );
  }
  return context;
};
