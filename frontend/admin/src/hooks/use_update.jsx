import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const use_update = (fetchedData, api, id) => {
  const [formData, setFormData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});

  const navigate = useNavigate();

  // Prefill the form when data is fetched
  useEffect(() => {
    if (fetchedData) {
      setFormData(fetchedData);
      setLoading(false);
    }
  }, [fetchedData]);

  // Handles form input change
  const handle_input_change = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Handles form submission
  const handle_submit = async (e) => {
    e.preventDefault();

    setError(null);
    setFieldErrors({});

    try {
      const { error: apiError, data } = await api(id, formData);

      if (apiError) {
        setError(apiError.message);
        setFieldErrors(apiError.fieldErrors || {});
        return;
      }

      alert("Updated successfully!");
      navigate(-1);
    } catch (err) {
      setError("An unexpected error occurred.");
      console.error("Update failed:", err);
    }
  };

  return {
    formData,
    handle_input_change,
    handle_submit,
    loading,
    error,
    fieldErrors, // renamed for clarity
  };
};

export default use_update;
