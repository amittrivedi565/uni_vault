import "../../styles/globals.css"

import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section"
import HeaderBar from "../../components/header_bar/header_bar"
import CreateFlow from "../../components/institute/create_flow/create_flow";
import Table from "../../components/institute/table/table"

import useFetchInstitutes from "../../hooks/institute/use_institute_get_institutes"
import useDeleteInstituteById from "../../hooks/institute/use_institute_delete_by_id"

import { useParams } from "react-router-dom"

function get() {
    const { id } = useParams()
    const { data, loading, error } = useFetchInstitutes()
    const handle_delete = useDeleteInstituteById(id)


    return (<>
        <div className="row">
            <Sidebar />

            <Section>
                <div className="institute-container">
                    <HeaderBar />
                    <CreateFlow />
                    {error && <p style={{ color: "red" }}>Error: {error}</p>}
                    {loading && <p>Loading...</p>}
                    {!loading && !error && data && (
                        <Table
                            data={data}
                            loading={loading}
                            error={error}
                            handle_delete={handle_delete}
                        />
                    )}
                </div>
            </Section>
        </div>
    </>)
}

export default get;