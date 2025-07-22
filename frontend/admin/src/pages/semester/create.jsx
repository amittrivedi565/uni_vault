import "../../styles/globals.css"
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section"
import HeaderBar from "../../components/header_bar/header_bar"
import Form from "../../components/form/post"

import { useCreateSemester } from "../../hooks/use_semester"

function create() {
    const { formData, handle_input_change, handle_submit, error, fieldErrors } = useCreateSemester();
    const semesterFields = [
        { name: "name", label: "Name", required: true },
        { name: "code", label: "Code", required: true },
        { name: "file", type : "file", label: "Upload Semester Syllabus", required: true },
    ];
    return (<>
        <div className="row">
            <Sidebar />
            <Section>
                <HeaderBar />
                {error && <p style={{ color: "red" }}>Error: {error}</p>}
                <Form
                    formData={formData}
                    handle_input_change={handle_input_change}
                    handle_submit={handle_submit}
                    error={error}
                    fieldErrors={fieldErrors}
                    fields={semesterFields}
                />
            </Section>
        </div>
    </>)
}

export default create;