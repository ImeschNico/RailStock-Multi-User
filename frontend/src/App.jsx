import "./App.css";
import { Home } from "./Pages/Home";
import { Layout } from "./components/layout";
import { Route, Routes } from "react-router-dom";
import { Bestand } from "./Pages/Bestand";
import { Suche } from "./Pages/Suche";
import { Lagerplatz } from "./Pages/Lagerplatz";
import { HerstellerBestand } from "./components/herstellerBestand";
import { Admin } from "./Pages/Admin";
import Login from "./Pages/Login";
import ProtectedRoute from "./components/protected-routes";
import Forbidden from "./Pages/Forbidden";
import { LokFilterPage } from "./Pages/LokFilterPage";
import { useAuth } from "./contexts/AuthContext";
import { Navigate } from "react-router-dom";
import Register from "./Pages/Register";

function App() {
  const { isAuthenticated } = useAuth();
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/* Index Route */}
        <Route
          index
          element={isAuthenticated ? <Navigate to="/home" /> : <Login />}
        />

        {/* Öffentliche Routes */}
        <Route
          path="/login"
          element={isAuthenticated ? <Navigate to="/home" /> : <Login />}
        />
        <Route
          path="/register"
          element={isAuthenticated ? <Navigate to="/home" /> : <Register />}
        />
        <Route path="/forbidden" element={<Forbidden />} />

        {/* Geschützte Routes */}
        <Route
          path="/home"
          element={
            <ProtectedRoute>
              <Home />
            </ProtectedRoute>
          }
        />
        <Route
          path="/loks/filter"
          element={
            <ProtectedRoute>
              <LokFilterPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/suche"
          element={
            <ProtectedRoute>
              <Suche />
            </ProtectedRoute>
          }
        />
        <Route
          path="/bestand"
          element={
            <ProtectedRoute>
              <Bestand />
            </ProtectedRoute>
          }
        />
        <Route
          path="/lagerplatz"
          element={
            <ProtectedRoute>
              <Lagerplatz />
            </ProtectedRoute>
          }
        />
        <Route
          path="/api/bestand/hersteller/:herstellerName"
          element={
            <ProtectedRoute>
              <HerstellerBestand />
            </ProtectedRoute>
          }
        />
        <Route
          path="/admin"
          element={
            <ProtectedRoute requiredRole="ADMIN">
              <Admin />
            </ProtectedRoute>
          }
        />
      </Route>
    </Routes>
  );
}

export default App;
