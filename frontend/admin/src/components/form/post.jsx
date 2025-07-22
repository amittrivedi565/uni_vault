import "../../styles/globals.css";

export default function DynamicForm({
  formData,
  handle_input_change,
  handle_submit,
  error,
  fieldErrors,
  fields,
}) {
  return (
    <div className="form-container" style={{ width: "90%", margin: "20px auto", padding: "20px" }}>
      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={handle_submit} encType="multipart/form-data">
        {fields.map(({ name, label, type = "text", required }) => (
          <div className="form-group" key={name}>
            <label className={`form-label${required ? " required" : ""}`}>{label}</label>

            {type === "textarea" ? (
              <textarea
                name={name}
                value={formData[name] || ""}
                onChange={handle_input_change}
                className="form-textarea"
                required={required}
              />
            ) : type === "file" ? (
              <input
                type="file"
                name={name}
                onChange={handle_input_change}
                className="form-input"
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

            {fieldErrors?.[name] && <p className="form-error">{fieldErrors[name]}</p>}
          </div>
        ))}

        <button type="submit" className="submit-button">Submit</button>
      </form>
    </div>
  );
}
