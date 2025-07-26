import { FaEdit, FaTrash } from "react-icons/fa";
import "./table.css";

const CSS_BASE_URL = import.meta.env.VITE_CSS_SERVICE;

export default function CommonTable({
  data = [],
  columns = [],
  handle_delete,
  preFixUrl,
  postFixUrl,
}) {
  function renderCell(col, row) {
    const value = row[col.key];

    if (col.type === "link" && col.display && col.uniqueLink) {
      return (
        <a
          className="td-a"
          href={`${CSS_BASE_URL}/download/${row.resource_id}`}
        >
          Click
        </a>
      );
    }

    if (col.type === "link" && col.display) {
      return (
        <a className="td-a" href={`/${preFixUrl}/details/${row.id}`}>
          {value}
        </a>
      );
    }

    if (col.type === "link") {
      return (
        <a className="td-a" href={`/${postFixUrl}/get/${row.id}`}>
          Next
        </a>
      );
    }
    return row[col.key];
  }

  const safeData = Array.isArray(data) ? data : [];

  const editLink = (row) => {
    return `/${preFixUrl}/update/${row.id}`;
  };

  return (
    <table className="audit-table" style={{ width: "90%" }}>
      <thead>
        <tr>
          {columns.map((col, index) => (
            <th key={index}>{col.label}</th>
          ))}
          <th>ACTIONS</th>
        </tr>
      </thead>

      <tbody>
        {safeData.length === 0 ? (
          <tr>
            <td
              colSpan={columns.length + 1}
              style={{ textAlign: "center", padding: "1rem" }}
            >
              No data found.
            </td>
          </tr>
        ) : (
          safeData.map((row) => (
            <tr key={row.id}>
              {columns.map((col) => (
                <td key={col.id}>{renderCell(col, row)}</td>
              ))}
              <td
                style={{ display: "flex", alignItems: "center", gap: "10px" }}
              >
                <a className="td-a" href={editLink(row)}>
                  <FaEdit size={15} />
                </a>
                <button onClick={() => handle_delete(row.id)}>
                  <FaTrash size={12} />
                </button>
              </td>
            </tr>
          ))
        )}
      </tbody>
    </table>
  );
}
