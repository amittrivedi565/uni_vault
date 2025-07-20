import { useEffect, useState } from "react";

/*
  Pass api as function, id for 1 : M relation such as institute : courses
*/

const use_fetch_by_id = (id, api) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!id) return;
    const fetch_by_id = async () => {
      setLoading(true);
      try {
        const result = await api(id);
        setData(result);
      } catch (err) {
        console.error(err);
        setError(err.message || "Unknown error");
      } finally {
        setLoading(false);
      }
    };

    fetch_by_id();
  }, [id, api]);

  return { data, loading, error };
};

export default use_fetch_by_id;
