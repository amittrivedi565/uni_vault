const BASE_URL = import.meta.env.VITE_BASE_ENDPOINT;

const request = async (method, path = "", data = null) => {
  const config = {
    method,
    headers: {
      "Content-Type": "application/json",
    },
  };

  if (data) {
    config.body = JSON.stringify(data);
  }

  const response = await fetch(`${BASE_URL}${path}`, config);

  if (!response.ok) {
    let message = await response.text();
    throw new Error(`API ${method} ${path} failed - ${response.status}: ${message}`);
  }

  if (response.status === 204) return true;
  return await response.json();
};

export const api = {
  get: (path = "") => request("GET", path),
  post: (path = "", data) => request("POST", path, data),
  put: (path = "", data) => request("PUT", path, data),
  del: (path = "") => request("DELETE", path),
};
