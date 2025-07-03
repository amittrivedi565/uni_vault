import { useState } from "react";
import { useNavigate } from "react-router-dom";
import institute_post_api from "../../../apis/institute/post"

const post_institute = () => {
  const navigate = useNavigate();
  const { error, post_data } = institute_post_api()

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
    const created_institute = post_data(formData)

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
      alert("Failed to submit. " + error);
    }
  };

  return {
    formData,
    handle_input_change,
    handle_submit,
    error
  };
};

export default post_institute;
