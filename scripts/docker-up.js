const execa = require('execa');
const { Listr } = require('listr2');
const chalk = require('chalk');
const stripAnsi = require('strip-ansi');
const waitOn = require('wait-on');

const logger = require('./lib/logger.js');
const base_path = require('./lib/base_path');
const config = require('./lib/config.js');

const localPort = 8080;
const containerPort = 8080;

// Import package.json
const info = require('../package.json');

const tasks = new Listr(
  [
    {
      title: `Build the services`,
      task: (ctx, task) =>
        execa('docker-compose', [
          'build',
        ])
    },
    {
      title: `Start the services`,
      task: (ctx, task) => 
        execa('docker-compose', ['up', '-d'])
        .then(() => waitOn({
          resources: [base_path.origin],
          delay: 0, // initial delay in ms, default 0
          interval: 1000, // poll interval in ms, default 250ms
          simultaneous: 1, // limit to 1 connection per resource at a time
          timeout: config.timeout, // timeout in ms, default 2m
          tcpTimeout: 1000, // tcp timeout in ms, default 300ms
          window: 1000, // stabilization time in ms, default 750ms
          validateStatus: function (status) {
            return status >= 0; // Any status is valid
          }
        }))
        .then(() => {
          task.output = `URL: \n${chalk.italic(base_path.origin)}\n`
        }),
      options: { persistentOutput: true },
    }
  ],
  { rendererOptions: { showErrorMessage: false, showTimer: true } }
);

tasks.run({}).catch(() => {
  // Log error to file
  logger.error(tasks.err, __filename);
});
