import { api } from "./api_client";

// Get all institutes
export const getAllInstitutes = () => api.get("/institutes");

// Get institute by ID
export const getInstituteById = (instituteId) => api.get(`/institutes/${instituteId}`);

// Post a new institute
export const postInstitute = (formData) => api.post("/institutes", formData);

// Update institute by ID
export const updateInstituteById = (instituteId, formData) => api.put(`/institutes/${instituteId}`, formData);

// Delete institute by ID
export const deleteInstitute = (instituteId) => api.del(`/institutes/${instituteId}`);
