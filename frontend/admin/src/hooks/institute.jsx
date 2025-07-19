// src/hooks/instituteHook.jsx

import use_get_all from "./methods/use_get_all";
import use_fetch_by_id from "./methods/use_get_by_id";
import use_post from "./methods/use_post";
import use_update from "./methods/use_update";
import use_delete from "./methods/use_delete";

import {
  getAllInstitutes,
  getInstituteById,
  postInstitute,
  updateInstituteById,
  deleteInstitute,
} from "../apis/institute_api";

const defaultInstituteData = {
  name: "",
  shortname: "",
  code: "",
  description: "",
};

export const useFetchAllInstitutes = () => use_get_all(getAllInstitutes);

export const useFetchInstituteById = (id) => use_fetch_by_id(id, getInstituteById);


export const useCreateInstitute = () =>
  use_post(postInstitute, defaultInstituteData, "/institutes");

export const useUpdateInstitute = () =>
  use_update(getInstituteById, updateInstituteById, defaultInstituteData, "/institutes");

export const useDeleteInstitute = () => use_delete(deleteInstitute);
