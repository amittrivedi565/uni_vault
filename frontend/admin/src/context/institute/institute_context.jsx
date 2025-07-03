import React, { createContext, useContext, useState } from 'react';

const institute_context = createContext();

const institute_task_provider = ({ children }) => {
  const [institutes, setInstitutes] = useState([]);

  const add_all_institutes = (new_institutes) =>
    setInstitutes([...institutes, new_institutes]);

  const delete_institute = (id) =>
    setInstitutes((prev) => prev.filter((inst) => inst.id !== id));

  const update_institute = (updated) =>
    setInstitutes((prev) =>
      prev.map((inst) =>
        inst.id === updated.id ? { ...inst, ...updated } : inst
      )
    );

  return (
    <institute_context.Provider
      value={{ institutes,add_all_institutes, delete_institute, update_institute }}
    >
      {children}
    </institute_context.Provider>
  );
};

const context_institute = () => useContext(institute_context)

export {
  institute_task_provider,
  context_institute
}