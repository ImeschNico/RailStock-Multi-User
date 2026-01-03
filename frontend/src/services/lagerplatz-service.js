import apiClient from "./api-client";

/**
 * Erstellt einen neuen Lagerplatz.
 *
 * @param {Object} param0 - Parameterobjekt
 * @param {string} param0.regal - Name des Regals
 * @param {string} param0.tablar - Name des Tablar
 * @returns {Promise<Object>} - DTO des neu erstellten Lagerplatzes
 */
export const createLagerplatz = async ({ regal, tablar }) => {
  const res = await apiClient.post("/lagerplatz/erstellen", {
    regal,
    tablar,
  });
  return res.data;
};
