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
        labelName: "Enter Subject Name",
        fieldType: "text",
        placeHolder: "e.g., Data Structures",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "shortname",
        labelName: "Enter Subject Shortname",
        fieldType: "text",
        placeHolder: "e.g., DSA",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "code",
        labelName: "Enter Subject Code",
        fieldType: "text",
        placeHolder: "e.g., DS101",
        inputField: "text",
        isRequired: true,
    },
    {
        fieldName: "description",
        labelName: "Enter Subject Description",
        fieldType: "text",
        inputField: "textarea",
        isRequired: false,
    },
];

function SubjectCreate() {
    const { semesterId } = useParams();
    const {
        handleInputChange,
        handleSubmit,
        fieldErrors,
        error,
        formData
    } = usePost(apis.subject.create, semesterId, "semesterId");

    return (
        <>
            <Navbar />
            <ErrorAlert error={error} />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Subject"} />
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

export default SubjectCreate;
