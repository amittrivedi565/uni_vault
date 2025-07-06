import { useNavigate } from "react-router-dom";
import institute_delete_api from "../../apis/institute/institute_delete_by_id";

const delete_institute_by_id = () => {
    const navigate = useNavigate();
    const handle_delete = async (e, id) => {
        e.preventDefault();
        const confirmed = window.confirm("Are you sure you want to delete this institute?");
        if (!confirmed) return;

        const success = await institute_delete_api(id)
        if (success) {
            alert("Institute deleted successfully!");
            navigate(0);
        } else {
            alert("Failed to delete institute.");
        }
    };
    return handle_delete;
};

export default delete_institute_by_id