import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import Wrapper from "../../components/institute/wrapper/wrapper";
import { useParams } from "react-router-dom";

import use_fetch_by_id from "../../hooks/use_get_by_id"
import {apis} from "../../apis/crud_generic"

function detail_view() {
    const { id } = useParams();

    const {
        data,
        loading,
        error
    } = use_fetch_by_id(apis.institute.getById,id);


    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;

    return (
        <div className="row">
            <Sidebar />
            <Section>
                <Wrapper
                    data={data}
                    courseCount={data?.courseCount}
                    branchCount={data?.branchCount}
                    semesterCount={data?.semesterCount}
                    subjectCount={data?.subjectCount}
                    unitCount={data?.unitCount}
                />
            </Section>
        </div>
    );
}

export default detail_view;
