const ErrorAlert = ({ error }) => {
  return error ? <div className="alert alert-danger">{error}</div> : null;
};

export default ErrorAlert;
