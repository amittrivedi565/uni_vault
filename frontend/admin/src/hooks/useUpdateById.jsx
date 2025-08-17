import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const useUpdate = (api, fetchedData,id) => {
  const [formData, setFormData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});

  const navigate = useNavigate();

  /* Prefill the fetched data passed to the function */
  useEffect(() => {
    if (fetchedData) {
      setFormData(fetchedData);
      setLoading(false);
    }
  }, [fetchedData]);

  // Handles form input change
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Handles form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    setError(null);
    setFieldErrors({});

    try {
      const{ error: apiError } = await api(id, formData);

      if (apiError) {
        setError(apiError.message);
        setFieldErrors(apiError.fieldErrors || {});
        return;
      }

      alert("Updated successfully!");
      navigate(-1);
    } catch (err) {
      setError("An unexpected error occurred.");
    }
  };

  return {
    handleInputChange,
    handleSubmit,
    formData,
    loading,
    error,
    fieldErrors
  };
};

export default useUpdate;
