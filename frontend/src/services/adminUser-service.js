import apiClient from "./api-client";

export const getAllUsers = () => apiClient.get("/admin/users/all");

export const changeUserRole = (id) =>
  apiClient.put(`/admin/users/${id}/role`, null, { params: { role: "ADMIN" } });
