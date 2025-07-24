import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/update";

import { useParams } from "react-router-dom";
import useFetchById from "../../hooks/use_get_by_id";
import useUpdate from "../../hooks/use_update";
import { apis } from "../../apis/crud_generic";

function SemesterUpdate() {
  const { id } = useParams();

  const { data, loading, error } = useFetchById(apis.semester.getById, id);
  const { formData, handle_input_change, handle_submit, fieldErrors } = useUpdate(
    data,
    apis.semester.updateById,
    id
  );

  const semesterFields = [
    { name: "name", label: "Name", required: true },
    { name: "code", label: "Code", required: true },
    { name: "resource_id", label: "File"},
  ];

  if (loading || !formData) return <p>Loading...</p>;
  if (!data) return <p>Loading...</p>;

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
            fieldErrors={fieldErrors}
            error={error}
            fields={semesterFields}
          />
        </div>
      </Section>
    </div>
  );
}

export default SemesterUpdate;
