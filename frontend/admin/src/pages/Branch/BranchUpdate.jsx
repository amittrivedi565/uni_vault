import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import UpdateForm from "../../components/Form/UpdateForm";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import MainLayout from "../../layouts/MainLayout";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";

import { apis } from "../../services/academicServiceApi";
import useGetById from "../../hooks/useGetById";
import useUpdate from "../../hooks/useUpdateById";
import { useParams } from "react-router-dom";
import Spinner from "../../components/Spinner/Spinner";

const requiredFields = [
    {
        fieldName: "name",
        labelName: "Enter Branch Name",
        fieldType: "text",
        placeHolder: "e.g., Computer Science",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "shortname",
        labelName: "Enter Branch Shortname",
        fieldType: "text",
        placeHolder: "e.g., CS",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "code",
        labelName: "Enter Branch Code",
        fieldType: "text",
        placeHolder: "e.g., CS101",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "description",
        labelName: "Enter Branch Description",
        fieldType: "text",
        inputField: "textarea",
        isRequired: false,
    },
];

function BranchUpdate() {
    const { branchId } = useParams();
    const { data: fetchedData, loading: fetchLoading, error: fetchError } = useGetById(apis.branch.getById, branchId);

    const {
        formData,
        loading: updateLoading,
        error: updateError,
        fieldErrors: ValidationError,
        handleInputChange,
        handleSubmit
    } = useUpdate(apis.branch.updateById, fetchedData, branchId);

    const isLoading = fetchLoading || updateLoading;

    return (
        <>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Branch"} />
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

export default BranchUpdate;
