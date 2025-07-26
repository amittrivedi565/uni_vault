import React, { useState } from "react";
import uploadFile from "../../apis/css_service";
import "../../styles.css";

function FileUploadForm({ onUploadSuccess, disableSubmitBtn = false }) {
  const [file, setFile] = useState(null);
  const [error, setError] = useState("");
  const [resourceId, setResourceId] = useState("");

  const handleChange = (e) => {
    setFile(e.target.files[0]);
    setError("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!file) {
      setError("Please select a file.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    const result = await uploadFile(formData);
    onUploadSuccess(result.id);
    setResourceId(result.id);
  };

  return (
    <div
      className="form-upload-group"
      style={{
        marginTop: resourceId ? "2em" : "10em",
        marginBottom: resourceId ? "2em" : "10em",
      }}
    >
      {!resourceId ? (
        <form onSubmit={handleSubmit} className="form-group-style">
          <div className="form-upload-group-heading">
            <h3 style={{ marginBottom: "1em" }}>Upload PDF file</h3>
            <p>*only after successful file upload, create form will appear</p>
          </div>

          <input id="file-upload" type="file" onChange={handleChange} />

          <button type="submit" className="submit-button">
            Submit
          </button>

          {error && <p style={{ color: "red" }}>{error}</p>}
        </form>
      ) : (
        <div>
          <h3 style={{ color: "green" }}>*File uploaded successfully!</h3>
        </div>
      )}
    </div>
  );
}

export default FileUploadForm;
