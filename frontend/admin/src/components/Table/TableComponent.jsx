import { useState } from "react";
import "./TableComponent.css"; // optional

function TableComponent() {
  const [expandedRow, setExpandedRow] = useState(null);

  const toggleExpand = (index) => {
    setExpandedRow(expandedRow === index ? null : index);
  };

  const rows = [
    {
      date: "Feb 21, 2020, 03:31:18 AM",
      category: "Auditing",
      summary: "Audit Log search performed",
      author: "admin",
      affected: [],
    },
    {
      date: "Feb 21, 2020, 03:30:36 AM",
      category: "Auditing",
      summary: "Audit Log search performed",
      author: "admin",
      affected: [],
    },
    {
      date: "Feb 21, 2020, 03:30:33 AM",
      category: "Repositories",
      summary: "RepositoryAccessedEvent",
      author: "admin",
      affected: ["AUD", "blueskyblitz"],
      details: {
        source: "172.27.84.24",
        nodeId: "7087a244-3889-4d0b-bde5-76b68ae3e73b",
        method: "Browser",
        target: "AUD/blueskyblitz",
      },
    },
    {
      date: "Feb 21, 2020, 03:29:47 AM",
      category: "Repositories",
      summary: "RepositoryAccessedEvent",
      author: "admin",
      affected: ["AUD", "blueskyblitz"],
    },
  ];

  return (
    <table className="audit-table">
      <thead>
        <tr>
          <th>Date</th>
          <th>Author</th>
          <th>Instiute Name</th>
          <th>Institue Code</th>
          <th>Affected Content(s)</th>
        </tr>
      </thead>
      <tbody>
        {rows.map((row, index) => (
          <>
            <tr key={index} className={row.details ? "expandable" : ""}>
              <td>
                {row.details && (
                  <span
                    className="expand-icon"
                    onClick={() => toggleExpand(index)}
                    style={{ cursor: "pointer", marginRight: "5px" }}
                  >
                    {expandedRow === index ? "▼" : "▶"}
                  </span>
                )}
                {row.date}
                <br />
                <span className="date-time">UTC</span>
              </td>
              <td>
                <a href="#" className="author-link">
                  {row.author}
                </a>
              </td>
              <td>{row.category}</td>
              <td>{row.summary}</td>
              <td>
                {row.affected &&
                  row.affected.map((item, i) => (
                    <a href="#" key={i} className="affected-objects">
                      {item}
                    </a>
                  ))}
              </td>
            </tr>
            {row.details && expandedRow === index && (
              <tr className="expandable-row">
                <td colSpan="5">
                  <div className="expandable-content">
                    <div className="detail-row">
                      <div className="detail-label">Category : </div>
                      <div className="detail-value">{row.details.source}</div>
                    </div>
                    <div className="detail-row">
                      <div className="detail-label">Object ID : </div>
                      <div className="detail-value">{row.details.nodeId}</div>
                    </div>
                    <div className="detail-row">
                      <div className="detail-label">Shortname: </div>
                      <div className="detail-value">{row.details.method}</div>
                    </div>
                  </div>
                </td>
              </tr>
            )}
          </>
        ))}
      </tbody>
    </table>
  );
}

export default TableComponent;
