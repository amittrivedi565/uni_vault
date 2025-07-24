import axios from "axios";

/*
  Base URLs from environment
*/
const UCS_BASE_URL = import.meta.env.VITE_UCS_SERVICE;

/*
  Axios instance with default config for UCS service
*/
const axiosInstance = axios.create({
  baseURL: UCS_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

/*
  Generic request function using Axios
*/
const request = async (method, path = "", data = null) => {
  try {
    const config = {
      method,
      url: path,
      data
    };
    const response = await axiosInstance(config);
    return response.data;

  } catch (error) {
    const responseData = error.response?.data;
    const message =
      typeof responseData === "string"
        ? responseData
        : responseData?.message || error.message || "Unknown error";

    const fieldErrors =
      typeof responseData === "object" && responseData?.fieldErrors
        ? responseData.fieldErrors
        : null;
    return {
      data: null,
      error: {
        message,
        fieldErrors
      }
    }
  }
};

/*
  API utility object with common HTTP methods
*/
export const api = {
  get: (path = "") => request("GET", path),
  post: (path = "", data) => request("POST", path, data),
  put: (path = "", data) => request("PUT", path, data),
  del: (path = "") => request("DELETE", path)
};
