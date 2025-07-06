import Sidebar from "../../components/sidebar/sidebar"
import Section from "../../components/section/section"
import Wrapper from "../../components/wrapper/wrapper"

function detail_view(){
    return(
        <>
            <div className="row">
                <Sidebar/>
                <Section>
                    <Wrapper/>
                </Section>
            </div>
        </>
    )
}

export default detail_view;