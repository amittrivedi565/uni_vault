import { useState } from "react";
import { useNavigate } from "react-router-dom";
import institute_post_api from "../../apis/institute/institute_post";

const post_institute = () => {
  const navigate = useNavigate();
  const { error, post_data, fieldErrors } = institute_post_api();

  const [formData, setFormData] = useState({
    name: "",
    shortname: "",
    code: "",
    description: "",
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
    const created_institute = await post_data(formData); 

    if (created_institute && !error) {
      alert("Institute created successfully!");
      setFormData({
        name: "",
        shortname: "",
        code: "",
        description: "",
      });
      navigate("/institutes");
    } else {
      // Don't show alert here for field-level errors
      if (error) alert("Failed to submit. " + error);
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

export default post_institute;
