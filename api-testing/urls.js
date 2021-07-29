// Load config
const config = require('./config.js');

// Base path
const base = `http://localhost:${config.port}`;

const urls = {
  base_path: new URL('', base),
  auth_path: new URL('/auth', base),
  api_path: new URL('/api/v1', base)
}


module.exports = urls;
