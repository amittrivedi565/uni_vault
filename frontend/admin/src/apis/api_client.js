/*
  Base URLs from environment
*/
const UCS_BASE_URL = import.meta.env.VITE_UCS_SERVICE;
const CSS_BASE_URL = import.meta.env.VITE_CSS_SERVICE;

/*
  Main request function for all HTTP methods
*/
const request = async (method, path = "", data = null, serviceName) => {
  const config = {
    method,
    headers: {
      "Content-Type": "application/json",
    },
  };

  // Handle JSON and FormData payloads
  if (data && !(data instanceof FormData)) {
    config.body = JSON.stringify(data);
  } else if (data instanceof FormData) {
    config.body = data;
    delete config.headers["Content-Type"]; // Let browser set it for multipart/form-data
  }

  const BASE_URL = serviceName === "CSS" ? CSS_BASE_URL : UCS_BASE_URL;

  const response = await fetch(`${BASE_URL}${path}`, config);

  if (!response.ok) {
    let error;
    try {
      error = await response.json(); // Try to parse JSON error response
    } catch {
      error = await response.text(); // Fallback to plain text
    }
    const errorMessage = error?.message || error || "Unknown error";
    throw new Error(errorMessage);
  }

  // No Content response
  if (response.status === 204) return true;

  const contentType = response.headers.get("content-type");

  if (contentType?.includes("application/json")) {
    return await response.json();
  }

  return await response.text(); // Plain string
};

/*
  Exported API utility functions
*/
export const api = {
  get: (path = "") => request("GET", path),
  post: (path = "", data) => request("POST", path, data),
  put: (path = "", data) => request("PUT", path, data),
  del: (path = "") => request("DELETE", path),
  upload: (path = "", formData) => request("POST", path, formData, "CSS"),
};
