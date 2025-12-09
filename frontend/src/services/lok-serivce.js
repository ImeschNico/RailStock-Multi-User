import apiClient from "./api-client";

export const filterLoks = async (filterObj) => {
  const params = new URLSearchParams(filterObj);
  const res = await apiClient.get(`/loks/filter?${params.toString()}`);
  return res.data;
};

//Api Aufruf für die freie Textsuche
export const searchLoks = async (searchParams) => {
  const params = new URLSearchParams(searchParams);
  const res = await apiClient.get(`/loks/filter?${params.toString()}`);
  return res.data;
};

//Neue Lok erstellen
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
