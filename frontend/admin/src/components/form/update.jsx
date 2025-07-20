import "../../styles/globals.css";

export default function UpdateForm({
  formData,
  handle_input_change,
  handle_submit,
  loading,
  error,
  fields, // array of { name, label, type, required }
}) {
  if (loading) return <p>Loading...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div style={{ width: "90%", margin: "20px auto", padding: "20px" }}>
      <div className="form-container">
        <form onSubmit={handle_submit}>
          {fields.map(({ name, label, type = "text", required }) => (
            <div className="form-group" key={name}>
              <label className="form-label">{label}</label>
              {type === "textarea" ? (
                <textarea
                  name={name}
                  value={formData[name] || ""}
                  onChange={handle_input_change}
                  className="form-textarea"
                  required={required}
                />
              ) : (
                <input
                  type={type}
                  name={name}
                  value={formData[name] || ""}
                  onChange={handle_input_change}
                  className="form-input"
                  required={required}
                />
              )}
            </div>
          ))}

          <button type="submit" className="submit-button">Update</button>
        </form>
      </div>
    </div>
  );
}
