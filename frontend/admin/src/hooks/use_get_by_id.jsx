import { useEffect, useState } from "react";

const use_fetch_by_id = (api, id) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!id) return;

    const fetchData = async () => {
      try {
        const response = await api(id);
        setData(response);
      } catch (err) {
        setError(err.message || "Unknown error");
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [id, api]);

  return { data, loading, error };
};

export default use_fetch_by_id;
