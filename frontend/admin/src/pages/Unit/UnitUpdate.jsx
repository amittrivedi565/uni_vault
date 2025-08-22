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
        labelName: "Enter Unit Name",
        fieldType: "text",
        placeHolder: "e.g., Recursion And Backtracking or Unit 1",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "shortname",
        labelName: "Enter Unit Shortname",
        fieldType: "text",
        placeHolder: "e.g., RB",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "code",
        labelName: "Enter Unit Code",
        fieldType: "text",
        placeHolder: "e.g., CS803",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "description",
        labelName: "Enter Unit Description",
        fieldType: "text",
        inputField: "textarea",
        isRequired: false,
    },
];

function UnitUpdate() {
    const { unitId } = useParams();
    const { data: fetchedData, loading: fetchLoading, error: fetchError } = useGetById(apis.unit.getById, unitId);

    const {
        formData,
        loading: updateLoading,
        error: updateError,
        fieldErrors: ValidationError,
        handleInputChange,
        handleSubmit
    } = useUpdate(apis.unit.updateById, fetchedData, unitId);

    const isLoading = fetchLoading || updateLoading;

    return (
        <>
            <Navbar />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Unit"} />
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

export default UnitUpdate;
