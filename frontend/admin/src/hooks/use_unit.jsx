import use_get_all from "./methods/use_get_all";
import use_fetch_by_id from "./methods/use_get_by_id";
import use_post from "./methods/use_post";
import use_update from "./methods/use_update";
import use_delete_by_id from "./methods/use_delete";

import { useCallback } from "react";
import { useParams } from "react-router-dom";

/*
  Unit API functions
*/
import {
  getAllUnitsBySubjectId,
  getUnitById,
  postUnit,
  updateUnitById,
  deleteUnit,
} from "../apis/unit_api";

/*
  Default state data
*/
const defaultUnitData = {
  name: "",
  shortname: "",
  code: "",
  description: "",
  resource_id: "", // This will be set after file upload
};

/*
  Hook to fetch all units by subjectId
*/
export const useFetchAllUnitsBySubjectId = (subjectId) => {
  const apiFn = useCallback(() => getAllUnitsBySubjectId(subjectId), [subjectId]);
  return use_get_all(apiFn);
};

/*
  Hook to fetch a single unit by ID
*/
export const useFetchUnitById = (unitId) =>
  use_fetch_by_id(unitId, getUnitById);

/*
  Hook to create a new unit
*/
export const useCreateUnit = () => {
  const { id } = useParams(); // assumes /:id is subjectId
  const initialData = { ...defaultUnitData, subjectId: id };
  return use_post((data) => postUnit(data, id), initialData, `/units/get/${id}`);
};

/*
  Hook to update a unit
*/
export const useUpdateUnit = (id) => {
  return use_update(
    () => getUnitById(id),
    updateUnitById,
    defaultUnitData,
    (data) => `/units/get/${data.subjectId}`
  );
};

/*
  Hook to delete a unit
*/
export const useDeleteUnitById = () => use_delete_by_id(deleteUnit);
