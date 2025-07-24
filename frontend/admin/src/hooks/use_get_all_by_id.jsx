/*
  * a custom hook to get one to many items by id
  * for eg, get all courses by institute id
  * takes an id and an api function as parameters
*/

import { useEffect, useState } from "react";

const use_fetch_all_by_id = (api,id) => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const response = await api(id);
        setData(response)
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    if (id) {
      fetchData();
    }
  }, [id, api]);
  return{data, loading, error};
}

export default use_fetch_all_by_id;