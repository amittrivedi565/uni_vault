import { api } from "./api_client";

// Get all units by subject id
export const getAllUnitsBySubjectId = (subjectId) => api.get(`/units/${subjectId}`);

// Get unit by unit id
export const getUnitById = (unitId) => api.get(`/units/fetchbyid/${unitId}`);

// Create unit under a subject
export const postUnit = (formData, subjectId) => {
  return api.post(`/units`, { ...formData, subjectId });
};

// Update unit by unit id
export const updateUnitById = (unitId, formData) => {
  return api.put(`/units/${unitId}`, formData);
};

// Delete unit by unit id
export const deleteUnit = (unitId) => api.del(`/units/${unitId}`);
