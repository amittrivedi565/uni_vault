import "../../styles.css";
import Sidebar from "../../components/sidebar/sidebar";
import Section from "../../components/section/section";
import HeaderBar from "../../components/header_bar/header_bar";
import Form from "../../components/form/post";

import { useCreateSemester } from "../../hooks/use_semester";
import  {useUpload}  from "../../hooks/use_upload";


function Create() {
    const {
        formData,
        handle_input_change,
        handle_submit,
        error,
        fieldErrors,
        setFormData
    } = useCreateSemester();

    const {
        file,
        handleFileChange,
        upload,
        uploading,
        error: uploadError
    } = useUpload();


    const semesterFields = [
        { name: "name", label: "Name", required: true },
        { name: "code", label: "Code", required: true },
        { name: "file", type: "file", label: "Upload Semester Syllabus", required: true },
    ];

    const customFileChange = (e) => {
        handle_input_change(e);   // update formData normally
        handleFileChange(e);      // update file in upload hook
    };

    const handleSubmitWrapper = async (e) => {
        e.preventDefault();
        try {
            // 1. Upload file first and get resourceId
            const resourceId = await upload();
            // 2. Set resourceId into formData
            setFormData((prev) => ({
                ...prev,
                resource_id: resourceId
            }));

            // 3. Submit the semester data with resourceId included
            const saved = await handle_submit();
            if (saved) {
                alert("Semester created with syllabus uploaded!");
            }
        } catch (err) {
            console.error("Error during file upload or semester creation:", err);
        }
    };

    return (
        <div className="row">
            <Sidebar />
            <Section>
                <HeaderBar />
                {error && <p style={{ color: "red" }}>Error: {error}</p>}

                <Form
                    formData={formData}
                    handle_input_change={customFileChange}
                    handle_submit={handleSubmitWrapper}
                    error={error}
                    fieldErrors={fieldErrors}
                    fields={semesterFields}
                />
            </Section>
        </div>
    );
}

export default Create;
