import { FaEdit, FaTrash } from "react-icons/fa";
import "./table.css";

export default function InstituteTable({data:institutes, loading, error, handle_delete}) {
  
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <table className="audit-table" style={{ width: "90%" }}>
      <thead>
        <tr>
          <th>Author</th>
          <th>Name</th>
          <th>Shortname</th>
          <th>Code</th>
          <th>Courses & More</th>
          <th>details</th>
        </tr>
      </thead>
      <tbody>
        {institutes?.map((inst) => (
          <tr key={inst.id}>
            <td>YOU</td>
            <td><a href={`/institutes/details/${inst.id}`} className="institute-table-a">{inst.name} ↗</a></td>
            <td>{inst.shortname}</td>
            <td>{inst.code}</td>
            <td>
              <a className="td-a" href={`/institute/${inst.id}`}>Next</a>
            </td>
            <td style={{display:"flex",  alignContent:"center", justifyContent:"start"}}>
              <a className="td-a" href={`/institutes/update/${inst.id}`}>
                <FaEdit color="black" size={15} />
              </a>
              &nbsp;
              &nbsp;
              <button onClick={(e) => handle_delete(e, inst.id)}>
                <FaTrash size={12} />
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
