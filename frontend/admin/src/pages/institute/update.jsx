import "./style.css"
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section"
import HeaderBar from "../../components/header_bar/header_bar"
import Form from "../../components/institute/form/update"

function post() {
    return (<>
        <div className="row">
            <Sidebar />
            <Section>
                <HeaderBar/>
                <Form/>
            </Section>
        </div>
    </>)
}

export default post;