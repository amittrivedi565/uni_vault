/*
  * a custom hook to get all items without any filters
  * currently only used for institutes
  * takes an api function as parameter
*/

import { useEffect, useState } from "react";

const use_fetch_all = (api) => {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api();
        setData(response);
      } catch (err) {
        setError(err.message || "Unknown error");
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [api]);

  return { data, loading, error };
};

export default use_fetch_all;
