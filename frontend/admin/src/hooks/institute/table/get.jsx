import institute_api_get from "../../../apis/institute/get"; 
import { useEffect, useState } from "react";

const get_institutes = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const endpoint = import.meta.env.VITE_INSTITUTE_ENDPOINT;


  useEffect(() => {
    const fetchData = async () => {
      try {
        const jsonData = await institute_api_get(endpoint);
        setData(jsonData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [endpoint]);

  return { data, loading, error };
};


export default get_institutes;