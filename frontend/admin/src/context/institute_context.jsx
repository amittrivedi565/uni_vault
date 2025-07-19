import React, { createContext, useContext, useState } from "react";

const InstituteContext = createContext();

export const InstituteProvider = ({ children }) => {
  const [institutes, setInstitutes] = useState([]);

  const add_all_institutes = (new_institutes) => setInstitutes(new_institutes);
  const delete_institute = (id) =>
    setInstitutes((prev) => prev.filter((inst) => inst.id !== id));
  const update_institute = (updated) =>
    setInstitutes((prev) =>
      prev.map((inst) => (inst.id === updated.id ? { ...inst, ...updated } : inst))
    );

  return (
    <InstituteContext.Provider
      value={{ institutes, add_all_institutes, delete_institute, update_institute }}
    >
      {children}
    </InstituteContext.Provider>
  );
};

export const context_institute = () => {
  const ctx = useContext(InstituteContext);
  if (!ctx) throw new Error("context_institute must be used within a provider");
  return ctx;
};
