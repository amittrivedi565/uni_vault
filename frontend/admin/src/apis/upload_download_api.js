import { api } from "./api_client";

const uploadFile = (file) => {
  const formData = new FormData();
  formData.append("file", file);
  return api.upload("/uploads", formData); 
};

export {uploadFile};
