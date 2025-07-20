import { api } from "./api_client";

// Get all subjects by semester id
export const getAllSubjectsBySemesterId = (semesterId) => api.get(`/subjects/${semesterId}`);

// Get subject by subject id
export const getSubjectById = (subjectId) => api.get(`/subjects/fetchbyid/${subjectId}`);

// Create subject under a semester
export const postSubject = (formData, semesterId) => {
  return api.post(`/subjects`, { ...formData, semesterId });
};

// Update subject by subject id
export const updateSubjectById = (subjectId, formData) => {
  return api.put(`/subjects/${subjectId}`, formData);
};

// Delete subject by subject id
export const deleteSubject = (subjectId) => api.del(`/subjects/${subjectId}`);
