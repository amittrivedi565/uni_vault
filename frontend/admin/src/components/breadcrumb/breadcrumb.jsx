import React from "react";
import "../../styles.css";
import { useBreadCrumbContext } from "../../context/breadcrumb_context";

export default function Breadcrumb() {
  const { viewBreadcrumb } = useBreadCrumbContext();

  return (
    <div className="breadcrumb-container">
      {viewBreadcrumb.length == 0 ? (
        <p>No breadcrumbs available</p>
      ) : (
        <ul>
          {viewBreadcrumb.map((item, index) => (
            <li key={index}>{item}</li>
          ))}
        </ul>
      )}
    </div>
  );
}
