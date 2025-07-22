import { useNavigate, useParams } from "react-router-dom";
import { useState } from "react";


const use_post = (api, initialFormData, onSuccessNavigateTo = null) => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});
  const [formData, setFormData] = useState({
    ...initialFormData,
    id: id,
  });

  const handle_input_change = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handle_submit = async (e = null, overrideData = null) => {
    if (e?.preventDefault) e.preventDefault();

    try {
      const payload = overrideData || { ...formData };

      const created = await api(payload, id);

      if (created) {
        alert("Created successfully!");
        setFormData({ ...initialFormData, instituteId: id });
        if (onSuccessNavigateTo) navigate(onSuccessNavigateTo);
      } else {
        setError("Creation failed.");
      }
    } catch (err) {
      if (err.response?.data?.fieldErrors) {
        setFieldErrors(err.response.data.fieldErrors);
      }
      setError(err.message || "Failed to submit.");
    }
  };

  return {
    formData,
    setFormData,
    handle_input_change,
    handle_submit,
    error,
    fieldErrors,
  };
};

export default use_post;
