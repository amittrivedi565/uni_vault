import "../../styles.css";

import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import CreateFlow from "../../components/create_flow/create_flow";
import CommonTable from "../../components/table/table";
import { useParams } from "react-router-dom";

import { apis } from "../../apis/ucs_service";
import useFetchAllByParentId from "../../hooks/use_get_by_id";
import useDeleteById from "../../hooks/use_delete";

function CourseView() {
  const { id } = useParams();
  const {
    data,
    loading,
    error: fetchError,
  } = useFetchAllByParentId(apis.course.getAllByParentId, id);

  const { deleteById, error: deleteError } = useDeleteById(
    apis.course.deleteById,
  );

  const columns = [
    { key: "name", label: "Name", type: "text" },
    { key: "shortname", label: "Shortname", type: "text" },
    { key: "code", label: "Code", type: "text" },
    { key: "courseId", label: "More", type: "link" },
  ];

  return (
    <div className="row">
      <Sidebar />
      <Section>
        <div className="common-container">
          <HeaderBar />
          <CreateFlow label="Create Course" link={`/courses/create/${id}`} />
          {loading ? (
            <p>Loading...</p>
          ) : fetchError ? (
            <p style={{ color: "red" }}>{fetchError}</p>
          ) : (
            <>
              {deleteError && (
                <p style={{ color: "red", marginBottom: "10px" }}>
                  {deleteError}
                </p>
              )}
              <CommonTable
                data={data}
                columns={columns}
                preFixUrl={"courses"}
                postFixUrl={"branches"}
                handle_delete={deleteById}
              />
            </>
          )}
        </div>
      </Section>
    </div>
  );
}

export default CourseView;
