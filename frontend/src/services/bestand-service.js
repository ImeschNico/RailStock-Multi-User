import apiClient from "./api-client";

/**
 * Liefert den Bestand einer Lok anhand ihrer ArtNumber.
 *
 * @param {string} artNumber - Artikelnummer der Lok
 * @returns {Promise<Object>} - Bestand der Lok
 * @throws {Error} - Wenn der Bestand nicht geladen werden kann
 */
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

/**
 * Aktualisiert den Bestand einer Lok an einem Lagerplatz.
 *
 * @param {Object} param0 - Parameterobjekt
 * @param {string} param0.artNumber - Artikelnummer der Lok
 * @param {string} param0.regal - Name des Regals
 * @param {string} param0.tablar - Name des Tablars
 * @param {number} param0.menge - Neue Menge
 * @returns {Promise<Object>} - Aktualisierter Bestand
 */
export const updateBestand = async ({ artNumber, regal, tablar, menge }) => {
  const res = await apiClient.put(`/bestand/updateBestand`, {
    artNumber,
    regal,
    tablar,
    menge,
  });
  return res.data;
};

/**
 * Transferiert Bestand von einem Lagerplatz zu einem anderen.
 *
 * @param {Object} param0 - Parameterobjekt
 * @param {string} param0.artNumber - Artikelnummer der Lok
 * @param {string} param0.vonRegal - Quell-Regal
 * @param {string} param0.vonTablar - Quell-Tablar
 * @param {string} param0.zuRegal - Ziel-Regal
 * @param {string} param0.zuTablar - Ziel-Tablar
 * @param {number} param0.menge - Menge zum Transferieren
 * @returns {Promise<Object>} - Aktualisierter Bestand am Ziel
 */
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

/**
 * Liefert den Gesamtbestand aller Loks.
 *
 * @returns {Promise<Object[]>} - Array mit allen Beständen
 */
export const getGesamtBestand = async () => {
  const res = await apiClient.get(`/bestand/alle`);
  return res.data;
};
