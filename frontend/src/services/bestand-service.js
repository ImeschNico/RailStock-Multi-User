import apiClient from "./api-client";

export const getBestandByArtNumber = async (artNumber) => {
  try {
    const res = await apiClient.get(`/bestand/lok/${artNumber}`);
    return res.data; // Axios hat direkt .data
  } catch (error) {
    console.error(
      "Fehler beim Laden des Bestandes:",
      error.response?.data || error.message
    );
    throw new Error("Fehler beim Laden des Bestandes");
  }
};

export const updateBestand = async ({ artNumber, regal, tablar, menge }) => {
  const res = await apiClient.put(`/bestand/updateBestand`, {
    artNumber,
    regal,
    tablar,
    menge,
  });
  return res.data;
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
  return res.data;
};

export const getGesamtBestand = async () => {
  const res = await apiClient.get(`/bestand/alle`);
  return res.data;
};
