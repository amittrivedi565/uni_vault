// methods/use_delete_by_id.js
import { useState } from "react";
import { useNavigate } from "react-router-dom";

/*
  * a custom hook to delete item by id
  * takes an api function (like deleteById) and returns a function to call later with the id
*/
const use_delete_by_id = (api) => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const deleteById = async (id) => {
    if (!id) {
      setError("ID is required");
      return;
    }
    try {
      setLoading(true);
      await api(id);
      navigate(0); // go back after delete
    } catch (err) {
      setError(err.message || "Unknown error");
    } finally {
      setLoading(false);
    }
  };

  return { deleteById, loading, error };
};

export default use_delete_by_id;
