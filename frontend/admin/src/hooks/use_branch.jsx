import use_get_all from "./methods/use_get_all";
import use_fetch_by_id from "./methods/use_get_by_id";
import use_post from "./methods/use_post";
import use_update from "./methods/use_update";
import use_delete_by_id from "./methods/use_delete";

import { useCallback } from "react";
/*
  Branch api's
*/

import {
  getAllBranchesByCourseId,
  getBranchById,
  postBranch,
  deleteBranch,
  updateBranchById,
} from "../apis/branch_api"

/*
  Get id from the param
*/

import { useParams } from "react-router-dom";

/*
  Default state data
*/

const defaultBranchData = {
  name: "",
  shortname: "",
  code: "",
  description: "",
};


/*
  Id, Passing Api Call, Redirection Route.
*/

export const useFetchAllBranchesByCourseId = (courseId) => {
  const apiFn = useCallback(() => getAllBranchesByCourseId(courseId), [courseId]);
  return use_get_all(apiFn);
};

export const useFetchBranchById = (branchId) => use_fetch_by_id(branchId, getBranchById);

export const useCreateBranch = () => {
  const { id } = useParams();
  const initialData = { ...defaultBranchData, courseId: id };
  return use_post((data) => postBranch(data, id), initialData, `/branches/get/${id}`);
};

export const useUpdateBranch = (id) => {
  return use_update(
    () => getBranchById(id),
    updateBranchById,
    defaultBranchData,
    (data) => `/branches/get/${data.courseId}`
  );
};

export const useDeleteBranchById = () => use_delete_by_id(deleteBranch);
