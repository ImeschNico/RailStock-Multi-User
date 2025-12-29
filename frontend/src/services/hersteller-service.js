import apiClient from "./api-client";

/**
 * Liefert alle Hersteller.
 *
 * @returns {Promise<Object[]>} - Array mit Hersteller-Daten
 */
export const getHersteller = async () => {
  const res = await apiClient.get("/hersteller");
  return res.data;
};

/**
 * Liefert alle Loks eines bestimmten Herstellers.
 *
 * @param {string} herstellerName - Name des Herstellers
 * @returns {Promise<Object[]>} - Array von Loks des Herstellers
 */
export const getBestandByHersteller = async (herstellerName) => {
  const res = await apiClient.get("/loks/filter", {
    params: { herstellerName },
  });
  return res.data;
};
