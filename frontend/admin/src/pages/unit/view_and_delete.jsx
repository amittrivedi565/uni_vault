import "../../styles/globals.css";

import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import CreateFlow from "../../components/create_flow/create_flow";
import CommonTable from "../../components/table/table";

import { useFetchAllUnitsBySubjectId, useDeleteUnitById } from "../../hooks/use_unit";
import { handleDownload } from "../../hooks/methods/use_download"; // Ensure this is a named export
import { useParams } from "react-router-dom";

function View() {
    const { id } = useParams();
    const { data, loading, error } = useFetchAllUnitsBySubjectId(id);

    console.log("Data fetched:", data);
    const handle_delete = useDeleteUnitById();

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
            key: "resource_id",
            label: "Download Pdf",
            render: (row) =>
                row ? (
                    <button
                        onClick={() => handleDownload(row)}
                        style={{
                            background: "#4CAF50",
                            color: "white",
                            border: "none",
                            padding: "6px 12px",
                            borderRadius: "4px",
                            cursor: "pointer",
                        }}
                    >
                        Click
                    </button>
                ) : (
                    <span style={{ color: "gray" }}>No file</span>
                ),
        },

    ];

    return (
        <div className="row">
            <Sidebar />
            <Section>
                <div className="common-container">
                    <HeaderBar />
                    <CreateFlow label="Create Units" link={`/units/create/${id}`} />
                    <CommonTable
                        data={data}
                        loading={loading}
                        error={error}
                        columns={columns}
                        handle_delete={handle_delete}
                        editBaseUrl={(row) => `/units/update/${row.id}`}
                    />
                </div>
            </Section>
        </div>
    );
}

export default View;
