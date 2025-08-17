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

function UnitView() {
    const { subjectId } = useParams();
    const { data, loading, error: fetchError } = useGetAllById(apis.unit.getAllByParentId, subjectId);
    const { deleteById, error: deleteError } = useDeleteById(apis.unit.deleteById);

    const isError = fetchError || deleteError;

    const unitColumns = [
        { key: "name", label: "Unit Name", type: "text" },
        { key: "shortname", label: "Unit Shortname", type: "text" },
        { key: "code", label: "Unit Code", type: "text" },
        { key: "resource_id", label: "Download File", type: "link", link: (row) => `http://localhost:8010/api/download/${row.resource_id}` },
    ];

    const pyqpColumns = [
        { key: "subjectName", label: "Subject Name", type: "text" },
        { key: "subjectCode", label: "Subject Code", type: "text" },
        { key: "yearSession", label: " Year Session", type: "text" },
        { key: "resource_id", label: "Download File", type: "link", link: (row) => `http://localhost:8010/api/download/${row.resource_id}` },
    ];


    const handleDelete = useCallback((id) => {
        deleteById(id)
    }, [deleteById])
    const getEditLink = useCallback((id) => `/units/update/${id}`, [])

    return (
        <div>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Units & QuestionPapers"}/>
                <Breadcrumb createLink={`/units/create/${subjectId}`} addExtraCreateButton={true}/>
                {loading ? (
                    <Spinner />
                ) : isError ? (
                    <ErrorAlert error={fetchError || deleteError} />
                ) : (
                    <Table columns={unitColumns} fetchedData={data} onDelete={handleDelete} editLink={getEditLink} tableHeading="@Units" />
                )}
                <div className="container d-flex justify-content-end mt-4">
                    <a href={`/api/pyqp/${subjectId}`} className="btn btn-outline-primary">+</a>
                </div>
                <Table columns={pyqpColumns} tableHeading="@Question Papers" />
            </MainLayout>
        </div>
    );
}

export default UnitView;
