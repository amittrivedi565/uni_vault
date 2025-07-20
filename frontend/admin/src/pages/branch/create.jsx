import "../../styles/globals.css"
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section"
import HeaderBar from "../../components/header_bar/header_bar"
import Form from "../../components/form/post"

import {useCreateBranch} from "../../hooks/use_branch"

function create() {
    const { formData, handle_input_change, handle_submit, error, fieldErrors } = useCreateBranch();
    const branchFields = [
        { name: "name", label: "Name", required: true },
        { name: "code", label: "Code", required: true },
        { name: "shortname", label: "ShortName", required: true },
        { name: "description", label: "Description", type: "textarea" },
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
                    fields={branchFields}
                />
            </Section>
        </div>
    </>)
}

export default create;