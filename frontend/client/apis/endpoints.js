require('dotenv').config();
const client = require('./api_client');
const {log_error}  = require('../log_errors');


async function getInstitutes() {
  try {
    console.log(process.env.UCS_SERVICE)
    const response = await client.get(`${process.env.UCS_SERVICE}/institutes`);
    return response.data;
  } catch (error) {
    log_error(error);
    throw error;
  }
}

async function getSemestersByBranch(id) {
  try {
    const response = await client.get(`${process.env.UCS_SERVICE}/semesters/${id}`);
    return response.data;
  } catch (error) {
    log_error(error)
    throw error;
  }
}


module.exports = { getInstitutes, getSemestersByBranch };
