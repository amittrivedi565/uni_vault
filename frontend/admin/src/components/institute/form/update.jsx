import "./form.css";
import institute_update_hook from "../../../hooks/institute/form/update_by_id"
export default function update() {

  const { formData,
    handle_input_change,
    handle_submit,
    loading,
    error,
  } = institute_update_hook()

  if (loading) return <p>Loading...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div style={{ width: "90%", margin: "20px auto", padding: "20px" }}>
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
            />
          </div>
          <button type="submit" className="submit-button">Update</button>
        </form>
      </div>
    </div>
  );
}
