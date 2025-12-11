import apiClient from "./api-client";

/**
 * Filtert Loks anhand der angegebenen Kriterien.
 *
 * @param {Object} filterObj - Objekt mit Filterparametern (z. B. Hersteller, Epoche, Spur).
 * @returns {Promise<Object[]>} - Ein Array von Lok-Daten, die den Filterkriterien entsprechen.
 */
export const filterLoks = async (filterObj) => {
  const params = new URLSearchParams(filterObj);
  const res = await apiClient.get(`/loks/filter?${params.toString()}`);
  return res.data;
};

/**
 * Führt eine freie Textsuche nach Loks durch.
 *
 * @param {Object} searchParams - Objekt mit Suchparametern (z. B. name, artNumber).
 * @returns {Promise<Object[]>} - Ein Array von Loks, die der Suchanfrage entsprechen.
 */
//Api Aufruf für die freie Textsuche
export const searchLoks = async (searchParams) => {
  const params = new URLSearchParams(searchParams);
  const res = await apiClient.get(`/loks/filter?${params.toString()}`);
  return res.data;
};

/**
 * Erstellt eine neue Lok.
 *
 * @param {Object} formData - Daten der neuen Lok, z. B. ArtNumber, Bezeichnung, Typ, Hersteller.
 * @returns {Promise<Object>} - Das DTO der neu erstellten Lok.
 */
export const createLok = async (formData) => {
  const res = await apiClient.post(`/loks/admin/neu`, formData);
  return res.data;
};

/**
 * Bearbeitet / dupliziert eine Lok
 * @param {number} id - ID der zu bearbeitenden Original-Lok
 * @param {object} lokData - Änderungen für die neue Lok (ArtNumber optional)
 * @returns {Promise<object>} - zurückgegebene DTO der neuen Lok
 */
export const editLok = async (id, lokData) => {
  const res = await apiClient.put(`/loks/admin/${id}/edit`, lokData);
  return res.data;
};
