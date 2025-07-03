const fetch_institute_by_id = async (id) => {
  const endpoint = import.meta.env.VITE_INSTITUTE_ENDPOINT;
  const response = await fetch(`${endpoint}/${id}`);

  if (!response.ok) {
    throw new Error(`Failed to fetch institute. Status: ${response.status}`);
  }

  return await response.json();
};


export default fetch_institute_by_id