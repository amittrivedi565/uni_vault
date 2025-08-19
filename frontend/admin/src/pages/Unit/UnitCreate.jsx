import MainLayout from "../../layouts/MainLayout";
import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import CreateForm from "../../components/Form/CreateForm";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import UploadFile from "../../components/UploadFile/UploadFile";
import usePost from "../../hooks/usePost";
import { apis } from "../../services/imsApi";
import { useParams } from "react-router-dom";
import { useState } from "react";


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

function UnitCreate() {
    const { subjectId } = useParams();
    const [fileId, setFileId] = useState(null);
    const {
        handleInputChange,
        handleSubmit,
        fieldErrors,
        error,
        formData
    } = usePost(apis.unit.create, subjectId, "subjectId", fileId);


    return (
        <>
            <Navbar />
            <ErrorAlert error={error} />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Unit"} />

                {/* Pass callback to UploadFile to set fileId */}
                <UploadFile onFileUpload={setFileId} />

                {/* Render CreateForm only if fileId is available */}
                {fileId && (
                    <CreateForm
                        fileId={fileId}
                        requiredFields={requiredFields}
                        onChange={handleInputChange}
                        onSubmit={handleSubmit}
                        validationErrors={fieldErrors}
                        values={formData}
                        showResourceIdComponent={true}
                    />
                )}
            </MainLayout>
        </>
    );
}

export default UnitCreate;
