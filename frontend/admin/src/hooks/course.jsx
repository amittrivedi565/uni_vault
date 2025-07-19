import use_get_all from "./methods/use_get_all";
import use_fetch_by_id from "./methods/use_get_by_id";
import use_post from "./methods/use_post";
import use_update from "./methods/use_update";
import use_delete from "./methods/use_delete";

import {
  getAllCourses,
  getCourseById,
  postCourse,
  updateCourseById,
  deleteCourse,
} from "../apis/course_api";

import { useParams } from "react-router-dom";

const defaultCourseData = {
  name: "",
  shortname: "",
  code: "",
  description: "",
};

export const useFetchAllCourses = () => use_get_all(getAllCourses);

export const useFetchCourseById = (id) => use_fetch_by_id(id, getCourseById);

export const useCreateCourse = () => {
  const { id } = useParams(); 
  const initialData = { ...defaultCourseData, instituteId: id };
  return use_post((data) => postCourse(data, id), initialData, `/course/${id}`);
};

export const useUpdateCourse = () =>
  use_update(getCourseById, updateCourseById, defaultCourseData, "/courses");

export const useDeleteCourse = () => use_delete(deleteCourse);
