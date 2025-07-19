import "../../styles/globals.css";
import { Link } from "react-router-dom";

function CreateFlow({ label, link }) {
  return (
    <div className="create-flow-container">
      <h4>{label}</h4>
      &nbsp;
      <Link to={link}>+</Link>
    </div>
  );
}

export default CreateFlow;
