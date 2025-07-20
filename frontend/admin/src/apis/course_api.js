import { api } from "./api_client";

// Get all courses by institute
export const getAllCoursesByInstituteId = (instituteId) => api.get(`/courses/${instituteId}`);

// Get course by course id
export const getCourseById = (courseId) => api.get(`/courses/fetchbyid/${courseId}`);

// Create course
export const postCourse = (formData, instituteId) => {
  return api.post(`/courses`, { ...formData, instituteId });
};

// Update course by course id
export const updateCourseById = (courseId, formData) => {
  return api.put(`/courses/${courseId}`, formData);
};

// Delete course by course id
export const deleteCourse = (courseId) => api.del(`/courses/${courseId}`);
