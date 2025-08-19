import { useEffect, useState } from "react";

const useGetAll = (api) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await api();
        setData(response);
      } catch (err) {
        console.error("Error fetching data:", err);
        handleFetchError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [api]);

  const handleFetchError = (err) => {
    setError(err.message);
  };

  return { data, loading, error };
};

export default useGetAll;
