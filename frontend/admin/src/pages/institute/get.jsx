import "./style.css"
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section"
import HeaderBar from "../../components/header_bar/header_bar"
import CreateFlow from "../../components/institute/create_flow/create_flow";
import Table from "../../components/institute/table/table"

function get() {
    return (<>
        <div className="row">
            <Sidebar />
            <Section>
                <HeaderBar/>
                <CreateFlow />
                <Table />
            </Section>
        </div>
    </>)
}

export default get;