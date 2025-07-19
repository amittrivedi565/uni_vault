import { useEffect, useState } from "react";

const use_fetch_by_id = (id, fetchFunction) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!id) return;

    const fetch_by_id = async () => {
      setLoading(true);
      try {
        const result = await fetchFunction(id);
        setData(result);
      } catch (err) {
        console.error(err);
        setError(err.message || "Unknown error");
      } finally {
        setLoading(false);
      }
    };

    fetch_by_id();
  }, [id, fetchFunction]);

  return { data, loading, error };
};

export default use_fetch_by_id;
