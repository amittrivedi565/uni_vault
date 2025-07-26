import "../../styles.css";

import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import CreateFlow from "../../components/create_flow/create_flow";
import CommonTable from "../../components/table/table";

import { apis } from "../../apis/ucs_service";
import useGetAll from "../../hooks/use_get_all";
import useDeleteById from "../../hooks/use_delete";

function InstituteView() {
  const { data, loading, error: fetchError } = useGetAll(apis.institute.getAll);
  const { deleteById, error: deleteError } = useDeleteById(
    apis.institute.deleteById,
  );

  const columns = [
    { key: "name", label: "Name", type: "link", display: true },
    { key: "shortname", label: "Shortname", type: "text" },
    { key: "code", label: "Code", type: "text" },
    { key: "instituteId", label: "More", type: "link" },
  ];

  return (
    <div className="row">
      <Sidebar />
      <Section>
        <div className="common-container">
          <HeaderBar />
          <CreateFlow label="Create Institute" link="/institutes/create" />
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
                preFixUrl={"institutes"}
                postFixUrl={"courses"}
                handle_delete={deleteById}
              />
            </>
          )}
        </div>
      </Section>
    </div>
  );
}
export default InstituteView;
