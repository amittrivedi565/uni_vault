import { api } from "./api_client";

// Get all institutes
export const getAllInstitutes = () => api.get("/institute");

// Get institute by ID
export const getInstituteById = (id) => api.get(`/institute/${id}`);

// Post a new institute
export const postInstitute = (formData) => api.post("/institute", formData);

// Update institute by ID
export const updateInstituteById = (id, formData) => api.put(`/institute/${id}`, formData);

// Delete institute by ID
export const deleteInstitute = (id) => api.del(`/institute/${id}`);
