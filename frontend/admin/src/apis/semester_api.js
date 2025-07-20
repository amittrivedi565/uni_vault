import { api } from "./api_client";

// Get all semesters by branch id
export const getAllSemestersByBranchId = (branchId) => api.get(`/semesters/${branchId}`);

// Get semesters by sem id
export const getSemesterById = (semId) => api.get(`/semesters/fetchbyid/${semId}`);

// Create semesters by branch id
export const postSemester = (formData, branchId) => {
  return api.post(`/semesters`, { ...formData, branchId });
};

// Update semesters by sem id
export const updateSemesterById = (semId, formData) => {
  return api.put(`/semesters/${semId}`, formData);
};

// Delete semesters by sem id
export const deleteSemester = (semId) => api.del(`/semesters/${semId}`);
