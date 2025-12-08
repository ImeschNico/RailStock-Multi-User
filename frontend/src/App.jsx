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

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Login />} />
        {/*öffentleiche Routes */}
        <Route path="/login" index element={<Login />} />
        <Route path="/forbidden" element={<Forbidden />} />

        {/* GEschützte Routes für eingeloggte User*/}
        <Route
          path="/home"
          element={
            <ProtectedRoute>
              <Home />
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
          path="/hersteller"
          element={
            <ProtectedRoute>
              <HerstellerBestand />
            </ProtectedRoute>
          }
        />

        {/*Admin Route - nur für Admins*/}
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
