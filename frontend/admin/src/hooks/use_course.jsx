import use_get_all from "./methods/use_get_all";
import use_fetch_by_id from "./methods/use_get_by_id";
import use_post from "./methods/use_post";
import use_update from "./methods/use_update";
import use_delete_by_id from "./methods/use_delete";

import {useCallback} from "react"
/*
  Course Api's
*/

import {
  getAllCoursesByInstituteId,
  getCourseById,
  postCourse,
  updateCourseById,
  deleteCourse,
} from "../apis/course_api";

/*
  Get id from the param
*/

import { useParams } from "react-router-dom";

/*
  Default state data
*/
const defaultCourseData = {
  name: "",
  shortname: "",
  code: "",
  description: "",
};

/*
  Id, Passing Api Call, Redirection Route.
*/

export const useFetchAllCoursesByInstituteId = (instituteId) => {
  const apiFn = useCallback(() => getAllCoursesByInstituteId(instituteId), [instituteId]);
  return use_get_all(apiFn);
};

export const useFetchCourseById = (id) => use_fetch_by_id(id, getCourseById);

export const useCreateCourse = () => {
  const { id } = useParams();
  const initialData = { ...defaultCourseData, instituteId: id };
  return use_post((data) => postCourse(data, id), initialData, `/courses/get/${id}`);
};

export const useUpdateCourse = (id) => {
  return use_update(
    () => getCourseById(id),
    updateCourseById,
    defaultCourseData,
    (data) => `/courses/get/${data.instituteId}`
  );
};

export const useDeleteCourse = () => use_delete_by_id(deleteCourse);
