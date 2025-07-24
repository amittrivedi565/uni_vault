import "../../styles.css";

import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import CreateFlow from "../../components/create_flow/create_flow";
import CommonTable from "../../components/table/table";


import useGetAll from "../../hooks/use_get_all";
import useDeleteById from "../../hooks/use_delete";
import {apis} from "../../apis/crud_generic"

function view() {
  const { data, loading, error } = useGetAll(apis.institute.getAll);
  const { deleteById } = useDeleteById(apis.institute.deleteById);

  const columns = [
    { key: "author", label: "Author", render: () => "YOU" },
    {
      key: "name",
      label: "Name",
      type: "link",
      href: (row) => `/institutes/details/${row.id}`,
      display: (val) => `${val} ↗`,
    },
    { key: "shortname", label: "Shortname" },
    { key: "code", label: "Code" },
    {
      key: "id",
      label: "More",
      type: "link",
      href: (row) => `/courses/get/${row.id}`,
      display: () => "Next",
    },
  ];

  return (
    <div className="row">
      <Sidebar />
      <Section>
        <div className="common-container">
          <HeaderBar />
          <CreateFlow label="Create Institute" link="/institutes/create" />
          <CommonTable
            data={data}
            loading={loading}
            error={error}
            columns={columns}
            handle_delete={deleteById}
            editBaseUrl="/institutes/update/"
          />
        </div>
      </Section>
    </div>
  );
}

export default view;
