import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/post";
import FileUploadForm from "../../components/upload_form/upload_form";

import { useParams } from "react-router-dom";
import { apis } from "../../apis/ucs_service";
import useCreate from "../../hooks/use_post";

function SemesterCreate() {
  const { id } = useParams(); // branchId (parent)
  const {
    formData,
    setFormData,
    error,
    handle_input_change,
    handle_submit,
    fieldErrors,
  } = useCreate(apis.semester.create, id, "branchId");

  const semesterFields = [
    {
      name: "name",
      label: "Name",
      required: true,
      placeHolder: "Enter Semester Name, eg Semester One",
    },
    {
      name: "code",
      label: "Number",
      required: true,
      placeHolder: "Enter Semester Number, eg 1, 2, 3, etc.",
    },
  ];

  return (
    <div className="row">
      <Sidebar />
      <Section>
        <HeaderBar />
        {error && <p style={{ color: "red" }}>Error: {error}</p>}

        <FileUploadForm
          onUploadSuccess={(id) =>
            setFormData((prev) => ({ ...prev, resource_id: id }))
          }
        />

        {formData.resource_id && (
          <Form
            formData={formData}
            handle_input_change={handle_input_change}
            handle_submit={handle_submit}
            error={error}
            fieldErrors={fieldErrors}
            fields={semesterFields}
          />
        )}
      </Section>
    </div>
  );
}

export default SemesterCreate;
