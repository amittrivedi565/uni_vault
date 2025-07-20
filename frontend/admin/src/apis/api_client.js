/*
  Base url of the UCS Service
*/
const BASE_URL = import.meta.env.VITE_BASE_ENDPOINT;

/*
  method : Get, Post, Update...
  path : /route
  data : passed to a function or sate
*/

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

/*
  creating CRUD function, setting default values
*/

export const api = {
  
  /*
    get is function that takes path, data as parameter
    this will be passed to the internal request function
  */
  get: (path = "") => request("GET", path),
  post: (path = "", data) => request("POST", path, data),
  put: (path = "", data) => request("PUT", path, data),
  del: (path = "") => request("DELETE", path),
};
