import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom";

const usePost = (api, id, idType, resource_id) => {
  const [formData, setFormData] = useState({});
  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});
  const navigate = useNavigate();


  useEffect(() => {
    setFormData((prev) => {
      let updated = { ...prev };
      if (id && idType) {
        updated[idType] = id;
      }
      if (resource_id) {
        updated.resource_id = resource_id;
      }
      return updated;
    });
  }, [id, idType, resource_id]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { error } = await api(formData);

    if (error) {
      setError(error.message);
      setFieldErrors(error.fieldErrors || {});
      return;
    }
    alert("Submitted successfully!");
    navigate(-1);
  };

  return {
    formData,
    handleInputChange,
    handleSubmit,
    setFormData,
    fieldErrors,
    error
  };
};

export default usePost;
