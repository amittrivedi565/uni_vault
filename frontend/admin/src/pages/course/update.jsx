import "../../styles/globals.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/update";
import { useParams } from "react-router-dom";
import { useUpdateCourse } from "../../hooks/use_course";

function update() {
  const { id } = useParams(); // courseId
  const {
    formData,
    handle_input_change,
    handle_submit,
    loading,
    error,
    fieldErrors,
  } = useUpdateCourse(id);

  if (loading) return <p>Loading...</p>;
  const courseFields = [
    { name: "name", label: "Name", required: true },
    { name: "code", label: "Code", required: true },
    { name: "shortname", label: "ShortName", required: true },
    { name: "description", label: "Description", type: "textarea" },
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
            fields={courseFields}
          />
        </div>
      </Section>
    </div>
  );
}

export default update;
