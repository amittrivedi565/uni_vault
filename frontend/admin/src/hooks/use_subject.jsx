import use_get_all from "./methods/use_get_all";
import use_fetch_by_id from "./methods/use_get_by_id";
import use_post from "./methods/use_post";
import use_update from "./methods/use_update";
import use_delete_by_id from "./methods/use_delete";

import { useCallback } from "react";
import { useParams } from "react-router-dom";

/*
  Subject API functions
*/
import {
  getAllSubjectsBySemesterId,
  getSubjectById,
  postSubject,
  updateSubjectById,
  deleteSubject,
} from "../apis/subject_api";

/*
  Default state data
*/
const defaultSubjectData = {
  name: "",
  shortname: "",
  code: "",
  description: "",
  file: null, // File to be uploaded
};

/*
  Hook to fetch all subjects by semesterId
*/
export const useFetchAllSubjectsBySemesterId = (semesterId) => {
  const apiFn = useCallback(() => getAllSubjectsBySemesterId(semesterId), [semesterId]);
  return use_get_all(apiFn);
};

/*
  Hook to fetch a single subject by ID
*/
export const useFetchSubjectById = (subjectId) =>
  use_fetch_by_id(subjectId, getSubjectById);

/*
  Hook to create a new subject
*/
export const useCreateSubject = () => {
  const { id } = useParams(); // assumes URL has /:id where id is semesterId
  const initialData = { ...defaultSubjectData, semesterId: id };
  return use_post((data) => postSubject(data, id), initialData, `/subjects/get/${id}`);
};

/*
  Hook to update a subject
*/
export const useUpdateSubject = (id) => {
  return use_update(
    () => getSubjectById(id),
    updateSubjectById,
    defaultSubjectData,
    (data) => `/subjects/get/${data.semesterId}`
  );
};

/*
  Hook to delete a subject
*/
export const useDeleteSubjectById = () => use_delete_by_id(deleteSubject);
