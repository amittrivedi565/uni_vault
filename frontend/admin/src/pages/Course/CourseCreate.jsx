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

function CourseCreate() {
    const {instituteId} = useParams()
    const {
        handleInputChange,
        handleSubmit,
        fieldErrors,
        error,
        formData
    } = usePost(apis.course.create,instituteId,"instituteId");
    return (
        <>
            <Navbar />
            <ErrorAlert error={error} />
            <MainLayout>
                <Headerbar serviceName={"NoteX"} entityName={"Course"} />
                <CreateForm requiredFields={requiredFields} onChange={handleInputChange} onSubmit={handleSubmit} validationErrors={fieldErrors} values={formData} />
            </MainLayout>
        </>
    )
}

export default CourseCreate;