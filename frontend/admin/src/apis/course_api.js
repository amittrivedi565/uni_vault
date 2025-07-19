import { api } from "./api_client";

export const getAllCourses = () => api.get("/course");

export const getCourseById = (id) => api.get(`/course/fetchbyid/${id}`);

export const postCourse = (formData, instituteId) => {
  return api.post("/course", { ...formData, instituteId });
};

export const updateCourseById = (id, formData) => {
  return api.put(`/course/${id}`, formData);
};

export const deleteCourse = (id) => api.del(`/course/${id}`);
