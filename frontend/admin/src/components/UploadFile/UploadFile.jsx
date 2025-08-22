import { useState } from "react";
import uploadFile from "../../services/mediaServiceApi";
import Spinner from "../Spinner/Spinner";
import SuccessAlert from "../SuccessAlert/SuccessAlert";

function UploadFile({ onFileUpload }) {
    const [selectedFile, setSelectedFile] = useState(null);
    const [message, setMessage] = useState({ type: "", text: "" });
    const [loading, setLoading] = useState(false);
    const [uploaded, setUploaded] = useState(false);

    const handleFileChange = (e) => {
        setSelectedFile(e.target.files[0]);
        setMessage({ type: "", text: "" });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!selectedFile) {
            setMessage({ type: "error", text: "Please select a file to upload." });
            return;
        }

        const formData = new FormData();
        formData.append("file", selectedFile);
        setLoading(true);

        try {
            const response = await uploadFile(formData);
            const uploadedFileId = response?.id; // adapt to your API

            if (uploadedFileId) {
                setMessage({ type: "success", text: "File uploaded successfully!" });
                setUploaded(true); // ✅ hide form after success
                onFileUpload(uploadedFileId);
            } else {
                throw new Error("No file ID returned");
            }
        } catch (e) {
            console.error(e);
            setMessage({ type: "error", text: "Error uploading file. Please try again." });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container bg-white border mt-4 p-3">
            {loading ? (
                <Spinner />
            ) : uploaded ? (
                <SuccessAlert message={message.text}/>
            ) : (
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="formFile" className="form-label">Select PDF file</label>
                        <input
                            className="form-control"
                            type="file"
                            id="formFile"
                            accept=".pdf"
                            onChange={handleFileChange}
                        />
                    </div>

                    {message.text && (
                        <div className={`alert alert-${message.type === "error" ? "danger" : "success"}`}>
                            {message.text}
                        </div>
                    )}

                    <button type="submit" className="btn btn-primary" disabled={loading}>
                        Upload File
                    </button>
                </form>
            )}
        </div>
    );
}

export default UploadFile;
