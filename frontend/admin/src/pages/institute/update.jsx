import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/update";

import { useParams } from "react-router-dom";

import useFetchById from "../../hooks/use_get_by_id";
import useUpdate from "../../hooks/use_update";
import { apis } from "../../apis/ucs_service";

function UpdateInstitute() {
  const { id } = useParams();

  const {
    data: fetchedData,
    loading: fetchLoading,
    error: fetchError
  } = useFetchById(apis.institute.getById, id);

  const {
    formData,
    error: updateError,
    fieldErrors,
    loading: updateLoading,
    handle_input_change,
    handle_submit,
  } = useUpdate(fetchedData, apis.institute.updateById, id);

  const instituteFields = [
    { name: "name", label: "Name", required: true },
    { name: "shortname", label: "Short Name", required: true },
    { name: "code", label: "Code", required: true },
    { name: "description", label: "Description", type: "textarea" },
  ];


  return (
    <div className="row">
      <Sidebar />
      <Section>
        <div className="common-container">
          <HeaderBar />
          {fetchError && <p style={{ color: "red" }}>{fetchError}</p>}
          {!fetchLoading && formData && (
            <Form
              formData={formData}
              handle_input_change={handle_input_change}
              handle_submit={handle_submit}
              loading={updateLoading}
              error={updateError}
              fieldErrors={fieldErrors}
              fields={instituteFields}
            />
          )}
        </div>
      </Section>
    </div>
  );
}

export default UpdateInstitute;
