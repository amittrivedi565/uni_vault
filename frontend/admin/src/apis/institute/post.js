import { useState } from "react";

export default function post_institute() {
  const [error, setError] = useState(null);

  const post_data = async (formData) => {
    try {
      const response = await fetch(import.meta.env.VITE_INSTITUTE_ENDPOINT, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error("Failed to post data");
      }

      const result = await response.json();
      return result;
    } catch (err) {
      setError(err.message);
      return null;
    }
  };

  return { post_data, error };
}

