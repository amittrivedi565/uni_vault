import MainLayout from "../../layouts/MainLayout";
import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import Table from "../../components/Table/Table";
import Spinner from "../../components/Spinner/Spinner";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";

import { apis } from "../../services/imsApi";
import useDeleteById from "../../hooks/useDeleteById";
import useGetAllById from "../../hooks/useGetAllById";
import { useParams } from "react-router-dom";
import { useCallback } from "react";

function BranchView() {
    const { courseId } = useParams();
    const { data, loading, error: fetchError } = useGetAllById(apis.branch.getAllByParentId, courseId);
    const { deleteById, error: deleteError } = useDeleteById(apis.branch.deleteById);

    const columns = [
        { key: "name", label: "Branch Name", type: "text" },
        { key: "shortname", label: "Branch Shortname", type: "text" },
        { key: "code", label: "Branch Code", type: "text" },
        { key: "id", label: "Next", type: "link", link: (row) => `/semesters/${row.id}` },
    ];

    const isError = fetchError || deleteError;
    const handleDelete = useCallback((id)=>{
        deleteById(id)
    },[deleteById])
    const getEditLink = useCallback((id)=> `/branches/update/${id}`,[])

    return (
        <div>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Branches"} />
                <Breadcrumb createLink={`/branches/create/${courseId}`} />
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

export default BranchView;
