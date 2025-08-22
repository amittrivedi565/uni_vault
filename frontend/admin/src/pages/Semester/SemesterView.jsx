import MainLayout from "../../layouts/MainLayout";
import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import Table from "../../components/Table/Table";
import Spinner from "../../components/Spinner/Spinner";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import { useCallback } from "react";

import { apis } from "../../services/academicServiceApi";
import useDeleteById from "../../hooks/useDeleteById";
import useGetAllById from "../../hooks/useGetAllById";
import { useParams } from "react-router-dom";

function SemesterView() {
    const { branchId } = useParams();
    const { data, loading, error: fetchError } = useGetAllById(apis.semester.getAllByParentId, branchId);
    const { deleteById, error: deleteError } = useDeleteById(apis.semester.deleteById);

    const columns = [
        { key: "name", label: "Semester Name", type: "text" },
        { key: "code", label: "Semester Code", type: "text" },
        { key: "id", label: "Next", type: "link", link: (row) => `/subjects/${row.id}` },
    ];

    const isError = fetchError || deleteError;


    const handleDelete = useCallback((id) => {
        deleteById(id)
    }, [deleteById])
    const getEditLink = useCallback((id) => `/semesters/update/${id}`, [])

    return (
        <div>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Semesters"} />
                <Breadcrumb createLink={`/semesters/create/${branchId}`} />
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

export default SemesterView;
