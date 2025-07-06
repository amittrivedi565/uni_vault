import { useEffect, useState } from "react";
import institute_api_get from "../../apis/institute/institute_get_all"; 

const use_institute_get_all_institutes = () => {
  const [institutes, setInsitutes] = useState(null)
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const endpoint = import.meta.env.VITE_INSTITUTE_ENDPOINT;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const jsonData = await institute_api_get(endpoint);
        setInsitutes(jsonData)
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [endpoint]);
  return { data: institutes, loading, error };
};

export default use_institute_get_all_institutes;
