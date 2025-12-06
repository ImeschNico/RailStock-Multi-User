import apiClient from "./api-client";

export const getBestandByArtNumber = async (artNumber) => {
  const res = await apiClient.get(`/bestand/lok/${artNumber}`);
  if (!res.ok) throw new Error("Fehler beim Laden des Bestandes");
  return res.json();
};

export const updateBestand = async ({ artNumber, regal, tablar, menge }) => {
  const res = await apiClient.put(`/bestand/updateBestand`, {
    artNumber,
    regal,
    tablar,
    menge,
  });
  return res.json();
};

export const transferBestand = async ({
  artNumber,
  vonRegal,
  vonTablar,
  zuRegal,
  zuTablar,
  menge,
}) => {
  const res = await apiClient.put(`/bestand/transferBestand`, {
    artNumber,
    vonRegal,
    vonTablar,
    zuRegal,
    zuTablar,
    menge,
  });
  return res.json();
};

//Api aufruf zum sehen der Menge aller Bestände
export const getGesamtBestand = async () => {
  const res = await apiClient.get(`/bestand/alle`);
  return res.json();
};
