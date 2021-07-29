const path = require('path');
const fs = require('fs');
const { EventEmitter } = require('events');
const waitOn = require('wait-on');
const portfinder = require('portfinder');
const chalk = require('chalk');
const ora = require('ora');
const xml2js = require('xml2js');
const execa = require('execa');

const { exit } = require('process');
const { spawn } = require('child_process');
const base_path = require('./urls').base_path;

// Load config
const config = require('./config.js');

// During the test the env variable is set to test
process.env.NODE_ENV = 'test';
process.env.SPRING_JPA_HIBERNATE_DDL_AUTO = 'create-drop'
process.env.SERVER_PORT = 8080;

// Test folder
const test_folder = path.join(__dirname, 'tests');

// Server name
let appName = undefined;

const getList = (dir, file_list) => {
  if (!fs.existsSync(dir)) {
    return file_list;
  }

  const list = fs.readdirSync(dir);

  list
    .map((el) => path.join(dir, el))
    .forEach((el) => {
      const stats = fs.statSync(el);

      if (stats.isDirectory()) {
        file_list = getList(el, file_list);
      } else {
        file_list.push(el);
      }
    });

  return file_list;
};

const file_list = getList(test_folder, []);

const MyEvent = new EventEmitter();

let server; // It will be a spawn
let i = 0; // Index to go through the files
let stop_spinner;

MyEvent.on('start-test', () => {
  if (i < file_list.length) {
    portfinder
      .getPortPromise({
        port: config.port,
        stopPort: config.port,
      })
      .then(() => {
        // Add check if there is already a process running on port config.port
        // Start server
        server = spawn('docker-compose', [
          'up'
        ], {
          cwd: path.join(__dirname, '..',),
        });

        let spinner = ora({
          text: `Starting Spring server`,
          stream: process.stdout 
        }).start()

        // Get file
        // Start server
        waitOn({
          resources: ['http://localhost:8080/'],
          delay: 0, // initial delay in ms, default 0
          interval: 1000, // poll interval in ms, default 250ms
          simultaneous: 1, // limit to 1 connection per resource at a time
          timeout: 30000, // timeout in ms, default Infinity
          tcpTimeout: 1000, // tcp timeout in ms, default 300ms
          window: 1000, // stabilization time in ms, default 750ms
        })
          .then(() => {
            spinner.succeed(`Started Spring server`);
            // Server started
            // Start test on file `file_list[i]`
            console.log(
              `Start tests -> file: ${chalk.bold.cyan(
                path.relative(process.cwd(), file_list[i])
              )}`
            );
            const mocha = spawn(
              'npx',
              ['mocha', '--timeout', '10000', '--bail', `${file_list[i]}`],
              { shell: true, stdio: 'inherit' }
            );

            mocha.on('close', (code) => {
              MyEvent.emit('stop-test');
            });
          })
          .catch((err) => {
            spinner.fail(`${chald.red('Failed to start Spring server')}`);
            console.log(err);
            exit(1);
          });

        server.on('close', (code) => {
          if (code === 0) {
            stop_spinner.succeed('Stopped Spring server');

            // SIGTERM
            console.log(
              `Finished tests -> file: ${chalk.bold.cyan(
                path.relative(process.cwd(), file_list[i])
              )}`
            );
            i += 1;
            server = undefined;
            MyEvent.emit('start-test');
          } else {
            stop_spinner.fail('Error stoppping Spring server');
            let err = `Error with server`;
            if(spinner.isSpinning) {
              spinner.fail(err);
            }
            else {
              console.error(err)
            }
            exit(1);
          }
        });
      })
      .catch((err) => {
        console.error(`Port ${config.port} already in use`);
        exit(1);
      });
  }
});

MyEvent.on('stop-test', () => {
  // Stop server
  if (server) {
    stop_spinner = ora({
      text: `Stopping Spring server`,
      stream: process.stdout 
    })
    .start()

    let stop_server = spawn('docker-compose', ['down'], {
      cwd: path.join(__dirname, '..',),
    })

    stop_server.on('close', (code) => {
      if(code != 0) {
        console.log('docker-compose down FAILED');
        exit(1);
      }
    })
  }
});

const data = fs.readFileSync(
  path.join(__dirname, '..', 'server', 'pom.xml')
);

let build_spinner = ora({
  text: `Building Spring server`,
  stream: process.stdout 
})
.start()

execa('docker-compose', ['build'], {
  cwd: path.join(__dirname, '..',),
})
.then(() => {
  build_spinner.succeed(`Built Spring server`)
  const parser = new xml2js.Parser();
  return parser
  .parseStringPromise(data)
  .then((result) => {
    result = result.project;
    appName = `${result.artifactId}-${result.version}.jar`
    // Start the process
    MyEvent.emit('start-test');
  })
})
.catch((err) => {
  build_spinner.succeed(`Error building Spring server`)
  console.error(err);
  exit(1);
})