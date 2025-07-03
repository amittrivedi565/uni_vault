import "./form.css";
import institute_post_hook from "../../../hooks/institute/form/post"

export default function post() {

  const {
    formData,
    handle_input_change,
    handle_submit,
    error
  } = institute_post_hook()

  
  return (<>
    <div style={{ width: "90%", margin: "20px auto", padding: "20px" }}>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <div className="form-container">
        <form onSubmit={handle_submit}>
          <div className="form-group">
            <label className="form-label">Name</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handle_input_change}
              className="form-input"
              placeholder="Enter institute name"
              required
            />
          </div>

          <div className="form-group">
            <label className="form-label">Shortname</label>
            <input
              type="text"
              name="shortname"
              value={formData.shortname}
              onChange={handle_input_change}
              className="form-input"
              placeholder="Enter short name"
              required
            />
          </div>

          <div className="form-group">
            <label className="form-label">Code</label>
            <input
              name="code"
              value={formData.code}
              onChange={handle_input_change}
              type="text"
              className="form-input"
              placeholder="Enter institute code"
              required
            />
          </div>

          <div className="form-group">
            <label className="form-label">Description</label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handle_input_change}
              className="form-textarea"
              placeholder="Brief description about the institute..."
            ></textarea>
          </div>

          <button type="submit" className="submit-button">
            Submit
          </button>
            
        </form>
      </div>
    </div>
  </>)
}

