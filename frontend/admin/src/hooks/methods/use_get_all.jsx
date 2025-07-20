import { useEffect, useState } from "react";

/*
  Pass api as function
  Fetch records
*/

const use_fetch_all = (api) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetch_all = async () => {
      try {
        const result = await api();
        setData(result);
      } catch (err) {
        setError(err.message || "Unknown error");
      } finally {
        setLoading(false);
      }
    };
    fetch_all();
  }, [api]);

  return { data, loading, error };
};

export default use_fetch_all;
