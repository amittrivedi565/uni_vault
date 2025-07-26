import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/post";
import FileUploadForm from "../../components/upload_form/upload_form";

import { useParams } from "react-router-dom";
import { apis } from "../../apis/ucs_service";
import useCreate from "../../hooks/use_post";

function UnitCreate() {
  const { id } = useParams(); // subjectId
  const {
    formData,
    error,
    handle_input_change,
    handle_submit,
    fieldErrors,
    setFormData,
  } = useCreate(apis.unit.create, id, "subjectId");

  const unitFields = [
    { name: "name", label: "Name", required: true },
    { name: "code", label: "Code", required: true },
    { name: "shortname", label: "ShortName", required: true },
    { name: "description", label: "Description", type: "textarea" },
    ,
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
            fields={unitFields}
          />
        )}
      </Section>
    </div>
  );
}

export default UnitCreate;
