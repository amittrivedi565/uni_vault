import Navbar from "../../components/Navbar/Navbar";
import Headerbar from "../../components/Headerbar/Headerbar";
import CreateForm from "../../components/Form/CreateForm";
import ErrorAlert from "../../components/ErrorAlert/ErrorAlert";
import usePost from "../../hooks/usePost";
import { apis } from "../../services/academicServiceApi";
import MainLayout from "../../layouts/MainLayout";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import { data } from "autoprefixer";
import UploadFile from "../../components/UploadFile/UploadFile";

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
    inputField: "textarea",
    isRequired: false,
  },
];

function InstituteCreate() {
  const {
    handleInputChange,
    handleSubmit,
    fieldErrors,
    error,
    formData
  } = usePost(apis.institute.create, null, "instituteId");
  return (
    <>
      <Navbar />
      <ErrorAlert error={error} />
      <MainLayout>
        <Headerbar serviceName={"NoteX"} entityName={"Institute"} />
        <CreateForm requiredFields={requiredFields} onChange={handleInputChange} onSubmit={handleSubmit} validationErrors={fieldErrors} values={formData}/>
      </MainLayout>
    </>
  )
}

export default InstituteCreate;