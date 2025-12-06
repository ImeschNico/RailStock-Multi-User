import apiClient from "./api-client";

export const createLagerplatz = async ({ regal, tablar }) => {
  const res = await apiClient.post("/lagerplatz/erstellen", {
    regal,
    tablar,
  });
  return res.data;
};
