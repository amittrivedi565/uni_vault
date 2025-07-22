import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import CreateFlow from "../../components/create_flow/create_flow";
import CommonTable from "../../components/table/table";

import { useFetchAllCoursesByInstituteId, useDeleteCourse } from "../../hooks/use_course";
import { useParams } from "react-router-dom";

function view() {
    const { id } = useParams();
    const { data, loading, error } = useFetchAllCoursesByInstituteId(id)// pass id
    const handle_delete = useDeleteCourse()
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
            href: (row) => `/branches/get/${row.id}`,
            display: () => "Next",
        },
    ];

    return (
        <div className="row">
            <Sidebar />
            <Section>
                <div className="common-container">
                    <HeaderBar />
                    <CreateFlow label="Create Course" link={`/courses/create/${id}`} />
                    <CommonTable
                        data={data}
                        loading={loading}
                        error={error}
                        columns={columns}
                        handle_delete={handle_delete}
                        editBaseUrl={(row) => `/courses/update/${row.id}`} // dynamic URL
                    />
                </div>
            </Section>
        </div>
    );
}

export default view;
