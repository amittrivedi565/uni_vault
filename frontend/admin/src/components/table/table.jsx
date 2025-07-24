import { FaEdit, FaTrash } from "react-icons/fa";
import "./table.css";

function renderCell(col, row) {
  const value = row[col.key];

  if (col.render) {
    return col.render(value, row);
  }

  if (col.type === "link" && typeof col.href === "function") {
    const url = col.href(row);
    const displayText = col.display ? col.display(value, row) : value;
    return (
      <a className="td-a" href={url}>
        {displayText}
      </a>
    );
  }

  return value;
}

export default function CommonTable({
  data = [],
  loading,
  error,
  columns = [],
  handle_delete,
  showActions = true,
  editBaseUrl,
}) {
  if (loading) return <p>Loading...</p>;

  if (error) {
    return (
      <p style={{ color: "red" }}>
        {error instanceof Error ? error.message : String(error)}
      </p>
    );
  }

  const safeData = Array.isArray(data) ? data : [];

  const getEditLink = (row) =>
    typeof editBaseUrl === "function"
      ? editBaseUrl(row)
      : `${editBaseUrl}${row.id}`;

  return (
    <table className="audit-table" style={{ width: "90%" }}>
      <thead>
        <tr>
          {columns.map((col) => (
            <th key={col.key}>{col.label}</th>
          ))}
          {showActions && <th>Actions</th>}
        </tr>
      </thead>
      <tbody>
        {safeData.length === 0 ? (
          <tr>
            <td
              className="td-empty"
              colSpan={columns.length + (showActions ? 1 : 0)}
              style={{ textAlign: "center", padding: "1rem" }}
            >
              No data found.
            </td>
          </tr>
        ) : (
          safeData.map((row) => (
            <tr key={row.id}>
              {columns.map((col) => (
                <td key={col.key}>{renderCell(col, row)}</td>
              ))}
              {showActions && (
                <td style={{ display: "flex", alignItems: "center", gap: "10px" }}>
                  <a className="td-a" href={getEditLink(row)}>
                    <FaEdit size={15} />
                  </a>
                  <button onClick={() => handle_delete(row.id)}>
                    <FaTrash size={12} />
                  </button>
                </td>
              )}
            </tr>
          ))
        )}
      </tbody>
    </table>
  );
}
