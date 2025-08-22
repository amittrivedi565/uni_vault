import axios from "axios";

const MEDIA_SERVICE_BASE_URL = import.meta.env.VITE_MEDIA_SERVICE;

const UploadFile = async (formData) => {
  const response = await axios.post(`${MEDIA_SERVICE_BASE_URL}/uploads`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  return response.data;
};

export default UploadFile;
