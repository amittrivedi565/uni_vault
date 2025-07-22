import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const use_post = (api, initialFormData, onSuccessNavigateTo = null) => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});
  const [formData, setFormData] = useState({
    ...initialFormData,
    instituteId: id,
  });

  const handle_input_change = (e) => {
    const { name, value, type, files } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: type === "file" ? files[0] : value,
    }));
  };

  const handle_submit = async (e) => {
    if (e?.preventDefault) e.preventDefault();

    try {
      let fileResponse = null;

      if (formData.file instanceof File) {
        const fileForm = new FormData();
        fileForm.append("file", formData.file);

        const uploadRes = await fetch("http://localhost:8010/api/uploads", {
          method: "POST",
          body: fileForm,
          headers: {
            // 👇 Important: expect JSON back
            Accept: "application/json",
          },
        });

        if (!uploadRes.ok) throw new Error("File upload failed");

        fileResponse = await uploadRes.json();
      }

      const payload = {
        ...formData,
        ...(fileResponse?.id ? { resource_id: fileResponse.id } : {}),
      };

      delete payload.file;

      const created = await api(payload, id);

      if (created) {
        alert("Created successfully!");
        setFormData({ ...initialFormData, instituteId: id });
        if (onSuccessNavigateTo) navigate(onSuccessNavigateTo);
      } else {
        setError("Creation failed.");
      }
    } catch (err) {
      // Axios errors
      if (err.response?.data?.fieldErrors) {
        setFieldErrors(err.response.data.fieldErrors);
      }
      // Fallback error message
      setError(err.message || "Failed to submit.");
    }
  };

  return {
    formData,
    handle_input_change,
    handle_submit,
    error,
    fieldErrors,
  };
};

export default use_post;
