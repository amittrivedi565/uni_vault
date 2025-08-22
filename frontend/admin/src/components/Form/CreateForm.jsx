/*
  Dynamic form inputs, using arrays with fields such as label, name, type, value etc...
  Required fields shows a red * for
  values are passed for managing re-rendering conditions
  showResourceIdComponent shows RID for confirmation that file has been upload
  validationErrors are received from the API endpoints @POST, then given the specific field
*/

function CreateForm({
  requiredFields,
  validationErrors,
  onChange,
  onSubmit,
  values,
  showResourceIdComponent = false,
  fileId
}) {
  return (
    <div className="container bg-white p-4 my-4 border">
      <form onSubmit={onSubmit}>

        {showResourceIdComponent && <div className="mb-3">
          <label htmlFor="resource_id" className="form-label">
            File id 
          </label>
          <input
            name="resource_id"
            type="text"
            id="resource_id"
            className="form-control"
            value={fileId}
            disabled
          />
        </div>}
        {requiredFields.map((field, index) => {
          const hasError = validationErrors && validationErrors[field.fieldName];
          const value = values[field.fieldName] || "";

          return (
            <div className="mb-3" key={index}>
              <label htmlFor={field.fieldName} className="form-label">
                {field.labelName}
                {field.isRequired ? (
                  <span style={{ color: "red" }}>*</span>
                ) : (
                  <span className="text-muted"> (optional)</span>
                )}
              </label>

              {field.inputField === "textarea" ? (
                <>
                  <textarea
                    name={field.fieldName}
                    id={field.fieldName}
                    placeholder={field.placeHolder}
                    onChange={onChange}
                    rows="3"
                    className={`form-control ${hasError ? "is-invalid" : ""}`}
                    required={field.isRequired}
                    value={value}
                  />
                  {hasError && (
                    <div className="invalid-feedback">
                      {validationErrors[field.fieldName]}
                    </div>
                  )}
                </>
              ) : (
                <>
                  <input
                    name={field.fieldName}
                    type={field.fieldType}
                    id={field.fieldName}
                    placeholder={field.placeHolder}
                    className={`form-control ${hasError ? "is-invalid" : ""}`}
                    required={field.isRequired}
                    onChange={onChange}
                    value={value}
                  />
                  {hasError && (
                    <div className="invalid-feedback">
                      {validationErrors[field.fieldName]}
                    </div>
                  )}
                </>
              )}
            </div>
          );
        })}

        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default CreateForm;
