import use_get_all from "./methods/use_get_all";
import use_fetch_by_id from "./methods/use_get_by_id";
import use_post from "./methods/use_post";
import use_update from "./methods/use_update";
import use_delete_by_id from "./methods/use_delete";

import { useCallback } from "react";
import { useParams } from "react-router-dom";

/*
  Semester API functions
*/
import {
  getAllSemestersByBranchId,
  getSemesterById,
  postSemester,
  updateSemesterById,
  deleteSemester,
} from "../apis/semester_api";
/*
  Default state data
*/
const defaultSemesterData = {
  name: "",
  shortname: "",
  code: "",
  description: "",
  resource_id: "",
};

/*
  Hook to fetch all semesters by branchId
*/
export const useFetchAllSemestersByBranchId = (branchId) => {
  const apiFn = useCallback(() => getAllSemestersByBranchId(branchId), [branchId]);
  return use_get_all(apiFn);
};

/*
  Hook to fetch a single semester by ID
*/
export const useFetchSemesterById = (semesterId) =>
  use_fetch_by_id(semesterId, getSemesterById);

/*
  Hook to create a new semester
*/
export const useCreateSemester = () => {
  const { id } = useParams();
  const initialData = { ...defaultSemesterData, branchId: id };
  return use_post((data) => postSemester(data, id), initialData, `/semesters/get/${id}`);
};

/*
  Hook to update a semester
*/
export const useUpdateSemester = (id) => {
  return use_update(
    () => getSemesterById(id),
    updateSemesterById,
    defaultSemesterData,
    (data) => `/semesters/get/${data.branchId}`
  );
};

/*
  Hook to delete a semester
*/
export const useDeleteSemesterById = () => use_delete_by_id(deleteSemester);
