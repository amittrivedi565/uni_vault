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
      institute_name: "Medicaps University",
      institute_shortname: "MD",
      author: "admin",
      affected: [],
    },
    {
      date: "Feb 21, 2020, 03:30:36 AM",
      institute_name: "DAVV IET",
      institute_shortname: "DAVV IET",
      author: "admin",
      affected: [],
    },
    {
      date: "Feb 21, 2020, 03:30:33 AM",
      institute_name: "Women Engineering College",
      institute_shortname: "WEC",
      author: "admin",
      affected: [],
      details: {
        institute_code: "WEC",
        Obj_Id: "7087a244-3889-4d0b-bde5-76b68ae3e73b",
        transcation_id: "7087a244-3889-4d0b-bde5",
      },
    },
    {
      date: "Feb 21, 2020, 03:29:47 AM",
      institute_name: "Sushila Devi Bansal College Of Engineering",
      institute_shortname: "SDBCE",
      author: "admin",
      affected: [],
    },
  ];

  return (
    <table className="audit-table">
      <thead>
        <tr>
          <th>Date</th>
          <th>Author</th>
          <th>Instiute Name</th>
          <th>Institue Shortname</th>
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
              <td>{row.institute_name}</td>
              <td>{row.institute_shortname}</td>
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
                      <div className="detail-label">Code : </div>
                      <div className="detail-value">{row.details.institute_code}</div>
                    </div>
                    <div className="detail-row">
                      <div className="detail-label">Object ID : </div>
                      <div className="detail-value">{row.details.Obj_Id}</div>
                    </div>
                    <div className="detail-row">
                      <div className="detail-label">Transcation_ID: </div>
                      <div className="detail-value">{row.details.transcation_id}</div>
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
