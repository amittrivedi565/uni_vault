import "./system_info.css"
import { useParams } from "react-router-dom";
function quick_actions({data}) {
    return (
        <>
            <div className="quick-actions-container">
                <h2>System Information</h2>
                <div className="quick-actions-rows">
                    <p>Object ID</p>
                    <b className="value-box">{data.id}</b>
                </div>
                <div className="quick-actions-rows">
                    <p>Transcation ID</p>
                    <b className="value-box">xxxx-xxxx-xxxx-xxxx</b>
                </div>

                    <a href={`/institutes/update/${data.id}`}><button className="submit-button">Edit Info</button></a>
        
            </div>
        </>
    )
}

export default quick_actions;