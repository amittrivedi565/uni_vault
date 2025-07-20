import { api } from "./api_client";

// Get all branches by course id
export const getAllBranchesByCourseId = (courseId) => api.get(`/branches/${courseId}`);

// Get branch by branch id
export const getBranchById = (branchId) => api.get(`/branches/fetchbyid/${branchId}`);

// Create branch by course id
export const postBranch = (formData, courseId) => {
  return api.post(`/branches`, { ...formData, courseId });
};

// Update branch by branch id
export const updateBranchById = (branchId, formData) => {
  return api.put(`/branches/${branchId}`, formData);
};

// Delete branch by branch id
export const deleteBranch = (branchId) => api.del(`/branches/${branchId}`);
