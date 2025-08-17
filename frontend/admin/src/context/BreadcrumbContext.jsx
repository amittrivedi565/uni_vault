import { createContext, useState, useCallback, useEffect } from "react";

export const BreadcrumbContext = createContext();

export const BreadcrumbContextProvider = ({ children }) => {
  // Initialize breadcrumb from localStorage or empty array
  const [breadcrumb, setBreadcrumb] = useState(() => {
    try {
      const stored = localStorage.getItem("breadcrumb");
      return stored ? JSON.parse(stored) : [];
    } catch {
      return [];
    }
  });

  // Whenever breadcrumb changes, save it to localStorage
  useEffect(() => {
    localStorage.setItem("breadcrumb", JSON.stringify(breadcrumb));
  }, [breadcrumb]);

  const appendBreadcrumb = useCallback((label, path) => {
    setBreadcrumb((prev) => {
      if (prev.some((b) => b.path === path)) return prev;
      return [...prev, { label, path }];
    });
  }, []);


  const trimBreadcrumbAfter = useCallback((path) => {
    setBreadcrumb((prev) => {
      const index = prev.findIndex((b) => b.path === path);
      if (index === -1) return prev; // not found, do nothing
      return prev.slice(0, index + 1); // keep items up to the clicked one
    });
  }, []);


  const clearBreadcrumb = useCallback(() => {
    setBreadcrumb([]);
    localStorage.removeItem("breadcrumb");
  }, []);


  return (
    <BreadcrumbContext.Provider
      value={{ breadcrumb, appendBreadcrumb, trimBreadcrumbAfter, clearBreadcrumb }}
    >
      {children}
    </BreadcrumbContext.Provider>
  );
};
