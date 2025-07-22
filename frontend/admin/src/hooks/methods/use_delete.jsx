import { useNavigate } from "react-router-dom";

/*
  Passing delete function as param, handle_delete
  Entity name such as Institute...
*/

const use_delete_by_id = (deleteFunction, entityName = "Item") => {
  const navigate = useNavigate();
  const handle_delete = async (e, id) => {
    e.preventDefault();
    try {
      const ss = await deleteFunction(id)
      console.log(ss); 
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
