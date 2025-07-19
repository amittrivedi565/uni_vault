import "../../styles/globals.css";

export default function post({ formData, handle_input_change, handle_submit, error, fieldErrors }) {
  return (
    <>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <div style={{ width: "90%", margin: "20px auto", padding: "20px" }}>
        <div className="form-container">
          <form onSubmit={handle_submit}>
            {/* Name */}
            <div className="form-group">
              <label className="form-label required">Name</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handle_input_change}
                className="form-input"
                placeholder="Enter course name"
                required
              />
              {fieldErrors?.name && (
                <p className="form-error">{fieldErrors.name}</p>
              )}
            </div>

            {/* Shortname */}
            <div className="form-group">
              <label className="form-label required">Shortname</label>
              <input
                type="text"
                name="shortname"
                value={formData.shortname}
                onChange={handle_input_change}
                className="form-input"
                placeholder="Enter course shortname"
                required
              />
              {fieldErrors?.shortname && (
                <p className="form-error">{fieldErrors.shortname}</p>
              )}
            </div>

            {/* Code */}
            <div className="form-group">
              <label className="form-label required">Code</label>
              <input
                type="text"
                name="code"
                value={formData.code}
                onChange={handle_input_change}
                className="form-input"
                placeholder="Enter course code"
                required
              />
              {fieldErrors?.code && (
                <p className="form-error">{fieldErrors.code}</p>
              )}
            </div>

            {/* Description */}
            <div className="form-group">
              <label className="form-label">Description</label>
              <textarea
                name="description"
                value={formData.description}
                onChange={handle_input_change}
                className="form-textarea"
                placeholder="Brief description about the course..."
              ></textarea>
              {fieldErrors?.description && (
                <p className="form-error">{fieldErrors.description}</p>
              )}
            </div>

            <button type="submit" className="submit-button">
              Submit
            </button>
          </form>
        </div>
      </div>
    </>
  );
}
