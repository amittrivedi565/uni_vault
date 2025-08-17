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
        labelName: "Enter Institute Name",
        fieldType: "text",
        placeHolder: "e.g., Rajiv Gandhi Proudyogiki Vishwavidyalaya",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "shortname",
        labelName: "Enter Institute Shortname",
        fieldType: "text",
        placeHolder: "e.g., RGPV",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "code",
        labelName: "Enter Institute Code",
        fieldType: "text",
        placeHolder: "e.g., 12456",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "description",
        labelName: "Enter Institute Description",
        fieldType: "text",
        placeHolder: "Enter description",
        inputField: "textarea",
        isRequired: false,
    },
];

function InstituteUpdate() {
    const { instituteId } = useParams();
    const { data: fetchedData, loading: fetchLoading, error: fetchError } = useGetById(apis.institute.getById, instituteId);

    const {
        formData,
        loading: updateLoading,
        error: updateError,
        fieldErrors: ValidationError,
        handleInputChange,
        handleSubmit
    } = useUpdate(apis.institute.updateById, fetchedData, instituteId);

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

export default InstituteUpdate;
