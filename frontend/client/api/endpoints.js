require('dotenv').config();
const client = require('./api_client');
const {log_error}  = require('../log_errors');


async function get_institutes() {
  try {
    const response = await client.get(`${process.env.UCS_SERVICE}/institutes`);
    return response.data;
  } catch (error) {
    log_error(error);
    throw error;
  }
}

async function get_semesters_by_branch_id(id) {
  try {
    const response = await client.get(`${process.env.UCS_SERVICE}/semesters/${id}`);
    return response.data;
  } catch (error) {
    log_error(error)
    throw error;
  }
}


module.exports = { get_institutes, get_semesters_by_branch_id };
