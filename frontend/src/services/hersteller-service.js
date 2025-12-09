import apiClient from "./api-client";

export const getHersteller = async () => {
  const res = await apiClient.get("/hersteller");
  return res.data;
};

export const getBestandByHersteller = async (hersteller) => {
  const res = await apiClient.get(
    `/loks/filter?hersteller=${encodeURIComponent(hersteller)}`
  );
  return res.data;
};
