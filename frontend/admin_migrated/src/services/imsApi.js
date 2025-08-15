/*
  * CRUD operations for generic resources
*/

import { api } from "./apiClient";

export const template = (basePath) => ({
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
  * Example usage: template("institutes")
  * This will create an object with methods for CRUD operations on institutes.
  * basePath is the endpoint for the resource.
*/
export const apis = {
  institute: template("institutes"),
  course: template("courses"),
  branch: template("branches"),
  semester: template("semesters"),
  subject: template("subjects"),
  unit: template("units"),
};