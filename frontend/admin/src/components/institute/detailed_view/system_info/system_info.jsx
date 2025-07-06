import "./system_info.css"
function system_info() {
    return (
        <>
            <div className="system-info-container">
                    <h2>Academic Overview</h2>
                    <div className="system-info-meta-tags">Core Metrics</div>

                    <div className="system-info-stats-label system-info-container-row">
                        <a href="">Total Courses ↗</a>
                        <b>20</b>
                    </div>

                    <div className="system-info-stats-label system-info-container-row">
                        <p>Total Branches</p>
                        <b>20</b>
                    </div>

                    <div className="system-info-stats-label system-info-container-row">
                        <p>Total Subjects</p>
                        <b>20</b>
                    </div>

                    <div className="system-info-stats-label system-info-container-row">
                        <p>*Net Units</p>
                        <b>20</b>
                    </div>
                    
            </div>
        </>
    )
}

export default system_info;