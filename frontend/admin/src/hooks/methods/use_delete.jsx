import { useNavigate } from "react-router-dom";

const use_delete_by_id = (deleteFunction, entityName = "Item") => {
  const navigate = useNavigate();

  const handle_delete = async (e, id) => {
    e.preventDefault();

    const confirmed = window.confirm(`Are you sure you want to delete this ${entityName.toLowerCase()}?`);
    if (!confirmed) return;

    try {
      await deleteFunction(id); 
      alert(`${entityName} deleted successfully!`);
      navigate(0); 
    } catch (err) {
      const errorMessage =
        err?.message ||
        `Failed to delete ${entityName.toLowerCase()}. Please try again.`;
      alert(`Error: ${errorMessage}`);
    }
  };

  return handle_delete;
};

export default use_delete_by_id;
