const execa = require('execa');
const path = require('path');
const fs = require('fs');
const xml2js = require('xml2js');
const { Listr } = require('listr2');
const { exit } = require('process');
const chalk = require('chalk');
const waitOn = require('wait-on');

const logger = require('./lib/logger.js');
const base_path = require('./lib/base_path');
const config = require('./lib/config.js');

const optimizeBuild = require('./lib/optimize-build-client.js');

const tasks = new Listr(
  [
    {
      title: `Check if the client needs to be built`,
      task: (ctx, task) => {
        if (optimizeBuild()) {
          ctx.clientIsBuilt = false;
        } else {
          ctx.clientIsBuilt = true;
        }
      },
    },
    {
      title: `Build the client`,
      skip: (ctx) => ctx.clientIsBuilt,
      task: (ctx, task) =>
        execa('npm', ['run', 'build'], {
          cwd: path.join(__dirname, '..', 'client'),
        }),
    },
    {
      title: `Build the server`,
      task: (ctx, task) =>
        execa('mvn', ['package'], {
          cwd: path.join(__dirname, '..', 'server'),
        }),
    },
    {
      title: `Start the application`,
      task: (ctx, task) => {
        const data = fs.readFileSync(
          path.join(__dirname, '..', 'server', 'pom.xml')
        );
        const parser = new xml2js.Parser();
        return parser
          .parseStringPromise(data)
          .then((result) => {
            result = result.project;
            const appName = `${result.artifactId}-${result.version}.jar`;
            return execa('java', ['-jar', path.join('target', `${appName}`)], {
              cwd: path.join(__dirname, '..', 'server'),
            }).stdout.pipe(process.stdout);
          })
          .then(() => {
            task.output = task.output = `URL: \n${chalk.italic(base_path.origin)}\n`;
          });
      },
      options: { persistentOutput: true },
    },
  ],
  {
    rendererOptions: { showErrorMessage: false, showTimer: true },
    registerSignalListeners: false,
  }
);

tasks.run({}).catch(() => {
  // Log error to file
  logger.error(tasks.err, __filename);
});
