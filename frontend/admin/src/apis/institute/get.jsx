const get_institutes = async (endpoint) => {
  const response = await fetch(endpoint);
  if (!response.ok) {
    throw new Error("Error fetching data from ucs-service");
  }
  return await response.json();
};

export default get_institutes;
