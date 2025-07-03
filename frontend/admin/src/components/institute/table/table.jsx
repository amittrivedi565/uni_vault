import "./table.css";
import get_institutes from "../../../hooks/institute/table/get";
import delete_institute from "../../../hooks/institute/table/delete"
import { FaEdit, FaTrash } from "react-icons/fa";


export default function institute_table() {

  const { data: institutes, loading, error } =  get_institutes()
  const handle_delete = delete_institute()
  
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <table className="audit-table" style={{ width: "90%" }}>
      <thead>
        <tr>
          <th>Author</th>
          <th>Institute Name</th>
          <th>Institute Shortname</th>
          <th>Institute Code</th>
          <th>Course & More(s)</th>
          <th>Operation</th>
        </tr>
      </thead>
      <tbody>
        {Array.isArray(institutes) &&
          institutes.map((inst) => (
            <tr key={inst.id}>
              <td>admin</td>
              <td>{inst.name}</td>
              <td>{inst.shortname}</td>
              <td>{inst.code}</td>
              <td>
                <a className="td-a" href={`/institute/${inst.id}`}>next</a>
              </td>
              <td style={{ textAlign: "center" }} >
                <a className="td-a" href={`/institutes/update/${inst.id}`}><FaEdit color="#42526e" size={15} /></a>
                &nbsp;
                &nbsp;
                <button onClick={(e) => handle_delete(e,inst.id)}>
                  <FaTrash color="#42526e" size={12} />
                </button>
              </td>
            </tr>
          ))}
      </tbody>
    </table >
  );
}

