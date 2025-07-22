import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section"
import HeaderBar from "../../components/header_bar/header_bar"
import Form from "../../components/form/post"

import {useCreateCourse} from "../../hooks/use_course"

function create() {
    const { formData, handle_input_change, handle_submit, error, fieldErrors } = useCreateCourse();
    const courseFields = [
        { name: "name", label: "Name", required: true },
        { name: "shortname", label: "ShortName", required: true },
        { name: "code", label: "Code", required: true },
        { name: "description", label: "Description", type: "textarea" },
    ];
    return (<>
        <div className="row">
            <Sidebar />
            <Section>
                <HeaderBar />
                <Form
                    formData={formData}
                    handle_input_change={handle_input_change}
                    handle_submit={handle_submit}
                    fieldErrors={fieldErrors}
                    error={error}
                    fields={courseFields}
                />
            </Section>
        </div>
    </>)
}

export default create;