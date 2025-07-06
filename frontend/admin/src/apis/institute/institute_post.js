import { useState } from "react";

export default function usePostInstitute() {
  const [error, setError] = useState(null);       
  const [fieldErrors, setFieldErrors] = useState({}); 
  const post_data = async (formData) => {
    setError(null);
    setFieldErrors({});
    try {
      const response = await fetch(import.meta.env.VITE_INSTITUTE_ENDPOINT, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        if (response.status === 400) {
          const validationErrors = await response.json();
          setFieldErrors(validationErrors);
        } else {
          const errText = await response.text();
          setError("Server error: " + errText);
        }
        return null;
      }

      const result = await response.json();
      return result;

    } catch (err) {
      setError("Network error: " + err.message);
      return null;
    }
  };

  return { post_data, error, fieldErrors };
}
