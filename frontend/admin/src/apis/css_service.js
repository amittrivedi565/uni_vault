// src/services/uploadFile.js
import axios from "axios";

const CSS_BASE_URL = import.meta.env.VITE_CSS_SERVICE;

const uploadFile = async (formData) => {
  const response = await axios.post(`${CSS_BASE_URL}/uploads`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  return response.data;
};

export default uploadFile;
