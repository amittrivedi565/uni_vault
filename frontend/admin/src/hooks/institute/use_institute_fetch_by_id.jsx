import { useEffect, useState } from "react";
import institute_api_fetch_by_id from "../../apis/institute/institute_fetch_by_id";

const fetch_institute_by_id = (id) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!id) return;
    const fetch_by_id = async () => {
      try {
        const json = await institute_api_fetch_by_id(id);
        setData(json);
      } catch (err) {
        console.error(err);
        setError(err.message || "Unknown error");
      } finally {
        setLoading(false);
      }
    };

    fetch_by_id();
  }, [id]);

  return { data, loading, error };
}

export default fetch_institute_by_id