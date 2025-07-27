const axios = require('axios');

const client = axios.create({
  baseURL: process.env.API_BASE_URL,
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json'
  }
});

module.exports = client;
