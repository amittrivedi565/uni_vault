import "../../styles/globals.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/update";

import { useUpdateInstitute } from "../../hooks/use_institute"; // ⬅️ using new reusable hook

function post() {
  const {
    formData,
    handle_input_change,
    handle_submit,
    loading,
    error,
  } = useUpdateInstitute();

  if (loading) return <p>Loading...</p>;

  const instituteFields = [
    { name: "name", label: "Name", required: true },
    { name: "shortname", label: "Description", required: true },
    { name: "code", label: "Code", required: true },
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
            loading={loading}
            error={error}
            fields={instituteFields} 
          />
        </div>
      </Section>
    </div>
  );
}

export default post;
