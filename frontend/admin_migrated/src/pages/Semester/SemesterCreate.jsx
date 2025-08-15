import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import CreateForm from "../../components/Form/CreateForm";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import usePost from "../../hooks/usePost";
import { apis } from "../../services/imsApi";
import MainLayout from "../../layouts/MainLayout";
import { useParams } from "react-router-dom";

const requiredFields = [
    {
        fieldName: "name",
        labelName: "Enter Semester Name",
        fieldType: "text",
        placeHolder: "e.g., Fall 2025",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "code",
        labelName: "Enter Semester Code",
        fieldType: "text",
        placeHolder: "e.g., SEM2025",
        inputField: "text",
        isRequired: true,
    },
];

function SemesterCreate() {
    const { branchId } = useParams();
    const {
        handleInputChange,
        handleSubmit,
        fieldErrors,
        error,
        formData
    } = usePost(apis.semester.create, branchId, "branchId");

    return (
        <>
            <Navbar />
            <ErrorAlert error={error} />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Semester"} />
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

export default SemesterCreate;
