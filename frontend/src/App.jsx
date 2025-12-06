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

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="/bestand/:lokId" element={<Bestand />} />
        <Route path="/suche" element={<Suche />} />
        <Route path="/lagerplatz" element={<Lagerplatz />} />
        <Route path="/loks/filter" element={<HerstellerBestand />} />
        <Route path="/login" element={<Login />} />
        <Route path="/admin" element={<Admin />} />
      </Route>
    </Routes>
  );
}

export default App;
