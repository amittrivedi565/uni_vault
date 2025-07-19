import "./academic_overview.css"
function system_info({data, courseCount, branchCount, semesterCount, subjectCount, unitCount}) {
    return (
        <>
            <div className="system-info-container">
                    <h2>Academic Overview</h2>
                    <div className="system-info-meta-tags">Core Metrics</div>

                    <div className="system-info-stats-label system-info-container-row">
                        <a href="">Total Courses ↗</a>
                        <b>{courseCount}</b>
                    </div>

                    <div className="system-info-stats-label system-info-container-row">
                        <p>Total Branches</p>
                        <b>{branchCount}</b>
                    </div>

                    <div className="system-info-stats-label system-info-container-row">
                        <p>Total Semesters</p>
                        <b>{semesterCount}</b>
                    </div>

                     <div className="system-info-stats-label system-info-container-row">
                        <p>Total Subjects</p>
                        <b>{subjectCount}</b>
                    </div>

                    <div className="system-info-stats-label system-info-container-row">
                        <p>Complete Units</p>
                        <b>{unitCount}</b>
                    </div>
                    
            </div>
        </>
    )
}

export default system_info;