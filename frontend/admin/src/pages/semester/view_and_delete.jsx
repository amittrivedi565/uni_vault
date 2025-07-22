import "../../styles/globals.css";

import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import CreateFlow from "../../components/create_flow/create_flow";
import CommonTable from "../../components/table/table";

import { useFetchAllSemestersByBranchId, useDeleteSemesterById } from "../../hooks/use_semester";
import { useParams } from "react-router-dom";

function view() {

    const { id } = useParams();
    
    const { data, loading, error } = useFetchAllSemestersByBranchId(id)
    const handle_delete = useDeleteSemesterById()

    const columns = [
        { key: "author", label: "Author", render: () => "YOU" },
        {
            key: "name",
            label: "Name",
            type: "link",
            href: (row) => `/`,
            display: (val) => `${val} ↗`,
        },
        { key: "code", label: "Code" },
        { key: "resource_id", label: "Syllabus" ,
            render: (row) => (
               <h3>row</h3>
            )       
        },
        {
            key: "id",
            label: "More",
            type: "link",
            href: (row) => `/subjects/get/${row.id}`,
            display: () => "Next",
        },
    ];

    return (
        <div className="row">
            <Sidebar />
            <Section>
                <div className="common-container">
                    <HeaderBar />
                    <CreateFlow label="Create Semesters" link={`/semesters/create/${id}`} />
                    <CommonTable
                        data={data}
                        loading={loading}
                        error={error}
                        columns={columns}
                        handle_delete={handle_delete}
                        editBaseUrl={(row) => `/semesters/update/${row.id}`}
                    />
                </div>
            </Section>
        </div>
    );
}

export default view;
