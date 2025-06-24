const client = require('./axios_client');
const log_error  = require('../log_errors');


async function getInstitutes() {
  try {
    const response = await client.get(process.env.API_INSTITUTES);
    return response.data;
  } catch (error) {
    log_error(error);
    throw error;
  }
}

async function getSemestersByBranch(id) {
  try {
    const response = await client.get(`http://localhost:8090/api/branch/${id}`);
    return response.data;
  } catch (error) {
    log_error(error)
    throw error;
  }
}

async function downloadResource(resourceId) {
  try {
    const response = await client.get(`http://localhost:8010/api/download/${resourceId}`);
    return response.data;
  } catch (error) {
    log_error(error);
  }
}

module.exports = { getInstitutes, getSemestersByBranch, downloadResource };
