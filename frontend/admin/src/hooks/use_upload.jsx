import { useState, useCallback } from "react";
import { uploadFile } from "../apis/upload_download_api"; // or semester_api, wherever upload API is

export const useUpload = () => {
  const [uploading, setUploading] = useState(false);
  const [error, setError] = useState(null);
  const [file, setFile] = useState(null);

  const handleFileChange = (e) => {
    const selected = e.target.files?.[0];
    if (selected) {
      setFile(selected);
    }
  };

  const upload = useCallback(async () => {
    if (!file) throw new Error("No file selected");
    setUploading(true);
    setError(null);
    try {
      const response = await uploadFile(file);
      return response.id;
    } catch (err) {
      console.error("Upload error:", err.message);
      setError(err.message || "Upload failed");
      throw err;
    } finally {
      setUploading(false);
    }
  }, [file]);

  return { file, handleFileChange, upload, uploading, error };
};

export default useUpload;