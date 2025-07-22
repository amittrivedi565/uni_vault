import "../../styles/globals.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/update";
import { useParams } from "react-router-dom";
import { useUpdateSemester } from "../../hooks/use_semester";

function update() {
  const { id } = useParams(); // courseId
  const {
    formData,
    handle_input_change,
    handle_submit,
    loading,
    error,
    fieldErrors,
  } = useUpdateSemester(id);

  if (loading) return <p>Loading...</p>;

   const semesterFields = [
        { name: "name", label: "Name", required: true },
        { name: "code", label: "Code", required: true },
        { name: "syllabus", label: "Update Uploaded File / Reupload", required: true },
    ];

  return (
    <div className="row">
      <Sidebar />
      <Section>
        <div className="common-container">
          <HeaderBar />
          {error && <p style={{ color: "red" }}>Error: {error}</p>}
          <Form
            formData={formData}
            handle_input_change={handle_input_change}
            handle_submit={handle_submit}
            error={error}
            fieldErrors={fieldErrors}
            fields={semesterFields}
          />
        </div>
      </Section>
    </div>
  );
}
export default update;
