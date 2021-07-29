const path = require('path');
const dotenv = require('dotenv');

// Load env file
dotenv.config({ path: path.join(__dirname, '..', '.env') });

const config = {
  port: process.env.SERVER_PORT || 8080,
  timeout: 120000
};

module.exports = config;
