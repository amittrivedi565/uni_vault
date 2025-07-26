import React, { createContext, useContext, useState } from "react";

const BreadCrumbContext = createContext();

export const BreadCrumbProvider = ({ children }) => {
  const [viewBreadcrumb, setBreadcrumb] = useState([]);

  const updateBreadcrumb = (newBreadcrumb) => {
    setBreadcrumb(newBreadcrumb);
  };

  console.log(viewBreadcrumb);

  return (
    <BreadCrumbContext.Provider value={{ viewBreadcrumb, updateBreadcrumb }}>
      {children}
    </BreadCrumbContext.Provider>
  );
};

export const useBreadCrumbContext = () => {
  const context = useContext(BreadCrumbContext);
  if (!context) {
    throw new Error(
      "useBreadCrumbContext must be used within BreadCrumbProvider",
    );
  }
  return context;
};
