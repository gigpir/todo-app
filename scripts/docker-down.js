const execa = require('execa');
const { Listr } = require('listr2');

const logger = require('./lib/logger.js');

// Import package.json
const info = require('../package.json');

const tasks = new Listr(
  [
    {
      title: `Stopping the services`,
      task: (ctx, task) =>
        execa('docker-compose', [
          'stop',
        ])
        .then(() => {
          task.title = `Stopped the services`;
        }),
    },
    {
      title: `Deleting containers, images and volumes`,
      task: (ctx, task) =>
        execa('docker-compose', [
          'down',
          '--rmi',
          'local',
          '-v'
        ])
        .then(() => {
          task.title = `Stopped the services`;
        }),
    }
  ],
  { rendererOptions: { showErrorMessage: false, showTimer: true } }
);

tasks.run({}).catch(() => {
  // Log error to file
  logger.error(tasks.err, __filename);
});
