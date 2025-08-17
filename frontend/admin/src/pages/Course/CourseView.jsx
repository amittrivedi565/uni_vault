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

function CourseView() {

    const { instituteId } = useParams();
    const { data, loading, error: fetchError } = useGetAllById(apis.course.getAllByParentId, instituteId)
    const { deleteById, error: deleteError } = useDeleteById(apis.course.deleteById)

    const columns = [
        { key: "name", label: "Course Name", type: "text" },
        { key: "shortname", label: "Course Shortname", type: "text" },
        { key: "code", label: "Course Code", type: "text" },
        { key: "id", label: "Next", type: "link", link: (row) => `/branches/${row.id}` },
    ];

    const isError = fetchError || deleteError

    const handleDelete = useCallback((id) => {
        deleteById(id)
    }, [deleteById])
    const getEditLink = useCallback((id) => `/courses/update/${id}`, [])

    return (
        <div>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Courses"} />
                <Breadcrumb createLink={`/courses/create/${instituteId}`} />
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

export default CourseView;
