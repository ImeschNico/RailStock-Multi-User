import { Lagerplatz } from "../Pages/Lagerplatz";

const BASE_URL = "http://localhost:8080/api";

export const fetchBestandByArtNumber = async (artNumber) => {
  const res = await fetch(`${BASE_URL}/bestand/lok/${artNumber}`);
  if (!res.ok) throw new Error("Fehler beim Laden des Bestandes");
  return res.json();
};

export const fetchUpdateBestand = async ({
  artNumber,
  regal,
  tablar,
  menge,
}) => {
  const res = await fetch(`${BASE_URL}/bestand/updateBestand`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ artNumber, regal, tablar, menge }),
  });
  return res.json();
};

export const fetchTransferBestand = async ({
  artNumber,
  vonRegal,
  vonTablar,
  zuRegal,
  zuTablar,
  menge,
}) => {
  const res = await fetch(`${BASE_URL}/bestand/transferBestand`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      artNumber,
      vonRegal,
      vonTablar,
      zuRegal,
      zuTablar,
      menge,
    }),
  });
  return res.json();
};

//API aufruf für die Dropdowns
export const fetchFilter = async (filterObj) => {
  const params = new URLSearchParams();

  Object.entries(filterObj).forEach(([key, value]) => {
    if (value) params.append(key, value);
  });

  const res = await fetch(`${BASE_URL}/loks/filter?${params.toString()}`);
  if (!res.ok) throw new Error("Fehler beim Abrufen der Daten");
  return res.json();
};

//Api Aufruf für die freie Textsuche
export const fetchSearchFilter = async (searchParams) => {
  const params = new URLSearchParams();

  Object.entries(searchParams).forEach(([key, value]) => {
    if (value) params.append(key, value);
  });

  const res = await fetch(`${BASE_URL}/loks/filter?${params.toString()}`);
  if (!res.ok) throw new Error("Fehler beim Aufrufen der Daten");
  return res.json();
};

export const createLagerplatz = async (regal, tablar) => {
  const res = await fetch(`${BASE_URL}/lagerplatz/erstellen`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      regal,
      tablar,
    }),
  });
  return await res.json;
};

//Aufruf der Api mit allen Herstellern
export const fetchHersteller = async () => {
  const res = await fetch(`${BASE_URL}/hersteller`);
  if (!res.ok) throw new Error("Fehler beim Laden der Hersteller")();
  return res.json();
};

//Aufruf Bestand nach Hersteller
export const fetchBestandByHersteller = async (herstellerName) => {
  const res = await fetch(`${BASE_URL}/bestand/hersteller/${herstellerName}`);
  if (!res.ok)
    throw new Error("Fehler beim Lden des Bestandes dieses Herstellers");
  return res.json();
};

//Neue Lok erstellen
export const createLok = async (formData) => {
  const res = await fetch(`${BASE_URL}/loks/admin/neu`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(formData),
  });
  return await res.json();
};

//Api aufruf zum sehen der Menge aller Bestände
export const fetchGesamtBestand = async () => {
  const res = await fetch(`${BASE_URL}/bestand/alle`);
  if (!res.ok) throw new Error("Fehler beim Laden des Gesamtbesatdnes");
  return res.json();
};
