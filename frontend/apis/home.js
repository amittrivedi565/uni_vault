const client = require('./axios_client');

async function getInstitutes() {
  try {
    const response = await client.get(process.env.API_INSTITUTES);
    return response.data;
  } catch (error) {
    console.error('Failed to fetch institutes:', error.message);
    throw error;
  }
}

module.exports = { getInstitutes };
