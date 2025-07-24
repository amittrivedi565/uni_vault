/*
  * CRUD operations for generic resources
*/

import { api } from "./api_client";

export const crud_generic = (basePath) => ({
  getAll: () => 
    api.get(`/${basePath}`),

  getAllByParentId: (parentId) => 
    api.get(`/${basePath}/${parentId}`),
  
  getById: (id) => 
    api.get(`/${basePath}/fetchbyid/${id}`),

  create: (formData, parentId, parentKey) => 
    api.post(`/${basePath}`, {
      ...formData,
      [parentKey]: parentId,
    }),

  updateById: (id, formData) => 
    api.put(`/${basePath}/${id}`, formData),

  deleteById: (id) => 
    api.del(`/${basePath}/${id}`),
});

/*
  * Example usage: crud_generic("institutes")
  * This will create an object with methods for CRUD operations on institutes.
  * basePath is the endpoint for the resource.
*/
export const apis = {
  institute: crud_generic("institutes"),
  course: crud_generic("courses"),
  branch: crud_generic("branches"),
  semester: crud_generic("semesters"),
  subject: crud_generic("subjects"),
  unit: crud_generic("units"),
};