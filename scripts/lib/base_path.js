// Load config
const config = require('./config.js');

// Base path
const base_path = new URL(`http://localhost:${config.port}`);

module.exports = base_path;
