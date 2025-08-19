import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import UpdateForm from "../../components/Form/UpdateForm";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import MainLayout from "../../layouts/MainLayout";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";

import { apis } from "../../services/imsApi";
import useGetById from "../../hooks/useGetById";
import useUpdate from "../../hooks/useUpdateById";
import { useParams } from "react-router-dom";
import Spinner from "../../components/Spinner/Spinner";

const requiredFields = [
    {
        fieldName: "name",
        labelName: "Enter Course Name",
        fieldType: "text",
        placeHolder: "e.g., Bachelor Of Technology",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "shortname",
        labelName: "Enter Course Shortname",
        fieldType: "text",
        placeHolder: "e.g., B.Tech",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "code",
        labelName: "Enter Course Code",
        fieldType: "text",
        placeHolder: "e.g., BT105",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "description",
        labelName: "Enter Course Description",
        fieldType: "text",
        inputField: "textarea",
        isRequired: false,
    },
];


function CourseUpdate() {
    const { courseId } = useParams();
    const { data: fetchedData, loading: fetchLoading, error: fetchError } = useGetById(apis.course.getById, courseId);

    const {
        formData,
        loading: updateLoading,
        error: updateError,
        fieldErrors: ValidationError,
        handleInputChange,
        handleSubmit
    } = useUpdate(apis.course.updateById, fetchedData, courseId);

    const isLoading = fetchLoading || updateLoading;

    return (
        <>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Institute"} />
                <ErrorAlert error={fetchError || updateError} />
                {isLoading ? (
                    <Spinner />
                ) : (
                    <UpdateForm
                        requiredFields={requiredFields}
                        onChange={handleInputChange}
                        onSubmit={handleSubmit}
                        validationErrors={ValidationError}
                        formData={formData}
                    />
                )}
            </MainLayout>
        </>
    );
}

export default CourseUpdate;
