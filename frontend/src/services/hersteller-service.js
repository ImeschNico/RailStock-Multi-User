import apiClient from "./api-client";

export const getHersteller = async () => {
  const res = await apiClient.get("/hersteller");
  return res.data;
};

export const getBestandByHersteller = async (name) => {
  const res = await apiClient.get(`/bestand/hersteller/${name}`);
  return res.data;
};
