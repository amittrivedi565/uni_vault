/*
  Dynamic form inputs, using arrays with fields such as label, name, type, value etc...
  Required fields shows a red asteric for
  values are passed for managing re-rendering conditions
  showResourceIdComponent shows RID for confirmation that file has been uploaed
  validationErrors are received from the API endpoints @POST, then given the specific field

  @GET, get data using id from API, then fills the required input fields {formData}
*/

function UpdateForm({ requiredFields, validationErrors, onChange, onSubmit, formData }) {
  return (
    <div className="container bg-white p-4 my-5 border rounded">
      <form onSubmit={onSubmit}>
        {requiredFields.map((field, index) => (
          <div className="mb-3" key={index}>

            <label htmlFor={field.fieldName} className="form-label">
              {field.labelName}
              {field.isRequired && <span style={{ color: "red" }}>*</span>}
            </label>

            {field.inputField === "textarea" ? (
              <textarea
                name={field.fieldName}
                id={field.fieldName}
                placeholder={field.placeHolder}
                onChange={onChange}
                rows="3"
                className={`form-control ${validationErrors[field.fieldName] ? "is-invalid" : ""}`}
                required={field.isRequired}
                value={formData[field.fieldName]}
              />
            ) : (
              <input
                name={field.fieldName}
                type={field.fieldType}
                id={field.fieldName}
                placeholder={field.placeHolder}
                className={`form-control ${validationErrors[field.fieldName] ? "is-invalid" : ""}`}
                required={field.isRequired}
                onChange={onChange}
                value={formData[field.fieldName]}
              />
            )}

            {validationErrors[field.fieldName] && (
              <div className="invalid-feedback">
                {validationErrors[field.fieldName]}
              </div>
            )}
          </div>
        ))}

        <button type="submit" className="btn btn-primary">Update</button>
      </form>
    </div>
  );
}

export default UpdateForm;
