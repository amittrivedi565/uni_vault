import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import CreateForm from "../../components/Form/CreateForm";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import usePost from "../../hooks/usePost";
import { apis } from "../../services/academicServiceApi";
import MainLayout from "../../layouts/MainLayout";
import { useParams } from "react-router-dom";

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

function BranchCreate() {
    const { courseId } = useParams();
    const {
        handleInputChange,
        handleSubmit,
        fieldErrors,
        error,
        formData
    } = usePost(apis.branch.create, courseId, "courseId");

    return (
        <>
            <Navbar />
            <ErrorAlert error={error} />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Branch"} />
                <CreateForm 
                    requiredFields={requiredFields} 
                    onChange={handleInputChange} 
                    onSubmit={handleSubmit} 
                    validationErrors={fieldErrors} 
                    values={formData} 
                />
            </MainLayout>
        </>
    );
}

export default BranchCreate;
