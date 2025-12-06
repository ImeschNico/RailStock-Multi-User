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
