import "./wrapper.css"
import InstituteHeading from "../../components/institute/detailed_view/institute_info/institute_info"
import SystemInfo from "../../components/institute/detailed_view/system_info/system_info"
import QuickView from "../../components/institute/detailed_view/quick_actions/quick_actions"

function wrapper(){
    return(
        <>
            <div className="wrapper">
                <InstituteHeading/>
                <SystemInfo/>
                {/* <QuickView/> */}
            </div>
        </>
    )
}

export default wrapper