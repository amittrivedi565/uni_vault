import "../../styles/globals.css"
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/institute/form/update";

import useUpdateInstitute from "../../hooks/institute/use_institute_update_by_id"

function Post() {
  const {
    formData,
    handle_input_change,
    handle_submit,
    loading,
    error,
  } = useUpdateInstitute()

  if (loading) return <p>Loading...</p>;

  return (
    <div className="row">
      <Sidebar />
      <Section>
        <div className="institute-container">
          <HeaderBar />
          {error && <p>Error: {error}</p>}
          <Form
            formData={formData}
            handle_input_change={handle_input_change}
            handle_submit={handle_submit}
            loading={loading}
            error={error}
          />
        </div>
      </Section>
    </div>
  );
}

export default Post;
