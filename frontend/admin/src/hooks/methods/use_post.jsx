import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const use_post = (postFn, initialFormData, onSuccessNavigateTo = null) => {
  const navigate = useNavigate();
  const { id } = useParams(); // optional: useful for entity relations
  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});
  const [formData, setFormData] = useState({
    ...initialFormData,
    instituteId: id, // only if required
  });

  const handle_input_change = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => {
      if (prev[name] === value) return prev;
      return { ...prev, [name]: value };
    });
  };

  const handle_submit = async (e) => {
    e.preventDefault();
    try {
      const created = await postFn(formData, id);
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
    handle_input_change,
    handle_submit,
    error,
    fieldErrors,
  };
};

export default use_post;
