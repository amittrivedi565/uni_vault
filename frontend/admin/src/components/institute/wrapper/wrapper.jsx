import "../../../styles/globals.css";
import InstituteHeading from "../detailed_view/institute_info/institute_info";
import AcademicOverview from "../detailed_view/academic_overview/academic_overview";
import SystemInfo from "../detailed_view/system_info/system_info";

function wrapper({ data, courseCount, branchCount, semesterCount, subjectCount, unitCount }) {
    return (
        <div className="wrapper">
            <InstituteHeading data={data} />
            <AcademicOverview
                data={data}
                courseCount={courseCount}
                branchCount={branchCount}
                semesterCount={semesterCount}
                subjectCount={subjectCount}
                unitCount={unitCount}
            />
            <SystemInfo data={data} />
        </div>
    );
}

export default wrapper;
