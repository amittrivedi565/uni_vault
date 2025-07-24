import {useState, useEffect} from "react"
import { useNavigate } from "react-router-dom";

const use_post = (api, id, idType) => {
  const [formData, setFormData] = useState({});
  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    if (id && idType) {
      setFormData((prev) => ({ ...prev, [idType]: id }));
    }
  }, [id, idType]);

  const handle_input_change = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handle_submit = async (e) => {
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
    setFormData,
    handle_input_change,
    handle_submit,
    fieldErrors,
    error,
  };
};

export default use_post;
