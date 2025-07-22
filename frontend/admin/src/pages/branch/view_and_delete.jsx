import "../../styles/globals.css";

import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import CreateFlow from "../../components/create_flow/create_flow";
import CommonTable from "../../components/table/table";

import { useFetchAllBranchesByCourseId, useDeleteBranchById } from "../../hooks/use_branch";
import { useParams } from "react-router-dom";

function view() {
    const { id } = useParams();
    const { data, loading, error } = useFetchAllBranchesByCourseId(id)
    const handle_delete = useDeleteBranchById()
    const columns = [
        { key: "author", label: "Author", render: () => "YOU" },
        {
            key: "name",
            label: "Name",
            type: "link",
            href: (row) => `/`,
            display: (val) => `${val} ↗`,
        },
        { key: "shortname", label: "Shortname" },
        { key: "code", label: "Code" },
        {
            key: "id",
            label: "More",
            type: "link",
            href: (row) => `/semesters/get/${row.id}`,
            display: () => "Next",
        },
    ];

    return (
        <div className="row">
            <Sidebar />
            <Section>
                <div className="common-container">
                    <HeaderBar />
                    <CreateFlow label="Create Branch" link={`/branches/create/${id}`} />
                    <CommonTable
                        error={error}
                        data={data}
                        loading={loading}
                        columns={columns}
                        handle_delete={handle_delete}
                        editBaseUrl={(row) => `/branches/update/${row.id}`}
                    />
                </div>
            </Section>
        </div>
    );
}

export default view;
