import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import institute_api_update from "../../apis/institute/institute_update_by_id"; 
import institute_hook_fetch_by_id from "./use_institute_fetch_by_id";

const update_institute = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const { data, loading, error } = institute_hook_fetch_by_id(id);

  const [formData, setFormData] = useState({
    name: "",
    shortname: "",
    code: "",
    description: "",
  });

  useEffect(() => {
    if (data) {
      setFormData({
        name: data.name || "",
        shortname: data.shortname || "",
        code: data.code || "",
        description: data.description || "",
      });
    }
  }, [data]);

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
      const updated = await institute_api_update(id, formData);
      if (updated) {
        alert("Institute updated successfully!");
        navigate("/institutes");
      } else {
        alert("Update failed.");
      }
    } catch (err) {
      alert("Failed to update. " + err.message);
    }
  };

  return {
    formData,
    handle_input_change,
    handle_submit,
    loading,
    error,
  };
};

export default update_institute;
