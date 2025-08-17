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
        labelName: "Enter Semester Name",
        fieldType: "text",
        placeHolder: "e.g., Semester 1",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "code",
        labelName: "Enter Semester Code",
        fieldType: "text",
        placeHolder: "e.g., SEM1",
        inputField: "text",
        isRequired: true,
    },
];

function SemesterUpdate() {
    const { semesterId } = useParams();
    const { data: fetchedData, loading: fetchLoading, error: fetchError } = useGetById(apis.semester.getById, semesterId);

    const {
        formData,
        loading: updateLoading,
        error: updateError,
        fieldErrors: ValidationError,
        handleInputChange,
        handleSubmit
    } = useUpdate(apis.semester.updateById, fetchedData, semesterId);

    const isLoading = fetchLoading || updateLoading;

    return (
        <>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Semester"} />
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

export default SemesterUpdate;
