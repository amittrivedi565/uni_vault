import { useEffect, useState } from "react";

const use_fetch_all = (fetchFunction) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetch_all = async () => {
      try {
        const result = await fetchFunction();
        setData(result);
      } catch (err) {
        setError(err.message || "Unknown error");
      } finally {
        setLoading(false);
      }
    };

    fetch_all();
  }, [fetchFunction]);

  return { data, loading, error };
};

export default use_fetch_all;
