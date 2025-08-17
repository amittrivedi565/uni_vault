import { useBreadcrumb } from "../../hooks/useBreadcrumb";
import { useNavigate } from "react-router-dom";

function Table({
  columns = [],
  fetchedData = [],
  onDelete,
  editLink,
  tableHeading=""
}) {

  const { appendBreadcrumb } = useBreadcrumb();
  const navigate = useNavigate();
  const renderCell = (col, row) => {
    if (col.type === "link") {
      return (
        <button
          className="td-a btn btn-link p-0"
          onClick={(e) => {
            e.preventDefault();
            const label = row.shortname || row.code || value;
            appendBreadcrumb(label, col.link(row));
            navigate(col.link(row));
          }}
        >
          ↗
        </button>
      );
    }
    return row[col.key];
  };

  const data = Array.isArray(fetchedData) ? fetchedData : [];

  return (
    <div className="container mt-5">
      <h5>{tableHeading}</h5>
      <div className="table-responsive">
        <table className="table table-hover border">
          <thead className="border-bottom">
            <tr>
              {columns.map((col, index) => (
                <th
                  key={index}
                  className="text-muted text-uppercase fw-normal py-3 px-3 text-center"
                  scope="col"
                >
                  {col.label}
                </th>
              ))}
              <th
                className="text-muted text-uppercase fw-normal py-3 px-3 text-center"
                scope="col"
              >
                actions
              </th>
            </tr>
          </thead>
          <tbody>
            {data.length === 0 ? (
              <tr>
                <td colSpan={columns.length + 1}>
                  <p className="font-italic text-center p-1">No data found.</p>
                </td>
              </tr>
            ) : (
              data.map((row) => (
                <tr key={row.id} className='text-center'>
                  {columns.map((col) => (
                    <td key={col.key}>{renderCell(col, row)}</td>
                  ))}
                  <td>
                    {/* Add action buttons or links here if needed */}
                    <a
                      className="btn btn-light btn-sm"
                      href={editLink(row.id)} // Use the editLink function to get the URL
                    >
                      Edit
                    </a>
                    &nbsp;
                    <button className="btn btn-light btn-sm" onClick={() => onDelete(row.id)}>Delete</button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Table;
