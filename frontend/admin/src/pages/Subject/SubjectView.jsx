import MainLayout from "../../layouts/MainLayout";
import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import Table from "../../components/Table/Table";
import Spinner from "../../components/Spinner/Spinner";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import { useCallback } from "react";

import { apis } from "../../services/imsApi";
import useDeleteById from "../../hooks/useDeleteById";
import useGetAllById from "../../hooks/useGetAllById";
import { useParams } from "react-router-dom";

function SubjectView() {
    const { semesterId } = useParams();
    const { data, loading, error: fetchError } = useGetAllById(apis.subject.getAllByParentId, semesterId);
    const { deleteById, error: deleteError } = useDeleteById(apis.subject.deleteById);

    const columns = [
        { key: "name", label: "Subject Name", type: "text" },
        { key: "shortname", label: "Subject Shortname", type: "text" },
        { key: "code", label: "Subject Code", type: "text" },
        { key: "id", label: "Next", type: "link", link: (row) => `/units/${row.id}` },
    ];

    const isError = fetchError || deleteError;


    const handleDelete = useCallback((id) => {
        deleteById(id)
    }, [deleteById])
    const getEditLink = useCallback((id) => `/subjects/update/${id}`, [])

    return (
        <div>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Subjects"} />
                <Breadcrumb createLink={`/subjects/create/${semesterId}`} />
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

export default SubjectView;
