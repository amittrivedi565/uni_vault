import { useCallback } from "react";
import MainLayout from "../../layouts/MainLayout";
import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import Table from "../../components/Table/Table";
import Spinner from "../../components/Spinner/Spinner";

import { apis } from "../../services/academicServiceApi";
import useGetAll from "../../hooks/useGetAll";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import useDeleteById from "../../hooks/useDeleteById";


function InstituteView() {

  const { data, loading, error: fetchError } = useGetAll(apis.institute.getAll);
  const { deleteById, error: deleteError } = useDeleteById(apis.institute.deleteById)

  const columns = [
    { key: "name", label: "Institute Name", type: "text" },
    { key: "shortname", label: "Institute Shortname", type: "text" },
    { key: "code", label: "Institute Code", type: "text" },
    { key: "id", label: "Next", type: "link", link: (row) => `/courses/${row.id}` },
  ];

  const isError = fetchError || deleteError

  const handleDelete = useCallback((id) => {
    deleteById(id);
  }, [deleteById]);

  const getEditLink = useCallback((id) => `/institutes/update/${id}`, []);


  return (
    <div>
      <Navbar />
      <MainLayout>
        <Headerbar serviceName={"NoteX"} entityName={"Institutes"} />
        <Breadcrumb createLink={"/institutes/create"} disableBreadcrumbComponent={true} />
        {loading ? (
          <Spinner />
        ) : isError ? (
          <ErrorAlert error={fetchError || deleteError} />
        ) : (
          <Table columns={columns} fetchedData={data} onDelete={handleDelete} editLink={getEditLink} />
        )}
      </MainLayout>
    </div>
  );
}

export default InstituteView;
