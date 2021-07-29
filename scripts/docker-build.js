const execa = require('execa');
const { Listr } = require('listr2');

const logger = require('./lib/logger.js');

// Import package.json
const info = require('../package.json');

const tasks = new Listr(
  [
    {
      title: `Build the services`,
      task: () =>
        execa('docker-compose', [
          'build'
        ])
    },
  ],
  { rendererOptions: { showErrorMessage: false, showTimer: true } }
);

tasks.run({}).catch(() => {
  // Log error to file
  logger.error(tasks.err, __filename);
});
