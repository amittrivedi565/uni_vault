import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/post";

import useCreate from "../../hooks/use_post";
import { apis } from "../../apis/crud_generic";
import { useParams } from "react-router-dom";

function SubjectCreate() {
  const { id } = useParams(); // id = branchId

  const {
    handle_input_change,
    handle_submit,
    formData,
    error,
    fieldErrors,
  } = useCreate(apis.subject.create, id, "semesterId");

  const subjectFields = [
    { name: "name", label: "Name", required: true },
    { name: "shortname", label: "ShortName", required: true },
    { name: "code", label: "Code", required: true },
    { name: "description", label: "Description", type: "textarea" },
  ];

  return (
    <div className="row">
      <Sidebar />
      <Section>
        <HeaderBar />
        {error && <p style={{ color: "red" }}>Error: {error}</p>}
        <Form
          formData={formData}
          handle_input_change={handle_input_change}
          handle_submit={handle_submit}
          fieldErrors={fieldErrors}
          error={error}
          fields={subjectFields}
        />
      </Section>
    </div>
  );
}

export default SubjectCreate;
