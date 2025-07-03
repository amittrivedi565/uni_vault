const update_institute_id = async (id, payload) => {
  const endpoint = import.meta.env.VITE_INSTITUTE_ENDPOINT;

  const response = await fetch(`${endpoint}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payload),
  });

  if (!response.ok) {
    throw new Error(`Failed to update institute. Status: ${response.status}`);
  }

  return true;
};

export default update_institute_id