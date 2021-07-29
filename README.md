# Srping + Vue3 template

Template for building a **Spring + Vue3** based web application

# Requirements

You need to have `mvn` and `docker` installed on your machine in order to take advantage of some of the scripts available in the script section



## Table of contents


* [Project structure](#project-structure)
* [Setup](#setup)
  + [Set a LICENSE](#set-a-license)
  + [Modify the package.json](#modify-the-packagejson)
  + [Install the dependencies](#install-the-dependencies)
  + [Open the application](#open-the-application)
  + [Start the application](#start-the-application)
  + [Modify eslint settings [OPTIONAL]](#modify-eslint-settings--optional-)
* [Client](#client)
* [Server](#server)
* [Documentation](#documentation)
* [Docker](#docker)
* [Scripts](#scripts)
  + [Install](#install)
  + [Start](#start)
  + [Build](#build)
  + [Lint](#lint)
    - [Lint](#lint-1)
    - [Fix](#fix)
  + [Lint local](#lint-local)
    - [Lint](#lint-2)
    - [Fix](#fix-1)
  + [Docker](#docker-1)
    - [Build](#build-1)
    - [Run](#run)
    - [Stop](#stop)
    - [Start](#start-1)
    - [Rm](#rm)
    - [Rmi](#rmi)



## Project structure

```
/
|    README.md
|    package.json
|    package-lock.json
|    Dockerfile
|    .dockerignore
|    .gitignore
|    .eslintrc.js
|
|___ api-testing
|    |    base_path.js
|    |    config.js
|    |    main.js
|    |    docker-main.js
|    |___ tests
|         |    todo.js
|
|___ client/
|    |    README.md
|    |    package.json
|    |    package-lock.json
|    |    vue.config.js
|    |    tailwind.config.js
|    |    babel.config.js
|    |    .gitignore
|    |    .eslintrc.js
|    |
|    |___ public/
|    |    |    favicon.ico
|    |    |    index.html
|    |    |    robots.txt
|    |
|    |___ src/
|         |    main.js
|         |    App.vue
|         |
|         |___ assets/
|         |    |    tailwind.css
|         |
|         |___ components/
|         |    |    Example.vue
|         |    |    Footer.vue
|         |    |    Navbar.vue
|         |
|         |___ router/
|         |    |    index.js
|         |    
|         |___ store/
|         |    |    index.js
|         |
|         |___ views/
|              |    Home.vue
|              |    About.vue
|        
|
|___ documentation/
|    |    README.md
|    |
|    |___ client/
|    |    |    README.md
|    |    |    ...
|    |
|    |___ server/
|         |    README.md
|         |    ...
|
|___ scripts/
|    |    docker-build.js
|    |    docker-run.js
|    |    docker-start-js
|    |    docker-stop.js
|    |    docker-rm.js
|    |    docker-rmi.js
|    |    build-client.js
|    |    start.js
|    |    
|    |___ lib/
|         |    logger.js
|         |    optimize-build-client.js
|        
|___ server/
     |    mwnw
     |    mwnw.cmd
     |    pom.xml
     |    server.iml
     |    HELP.md
     |    README.md
     |
     |___ src/
          |___ main/
          |    |___ java/
          |    |    |___ it.polito.det.template
          |    |    |    |___ controllers/
          |    |    |    |    |    ApiController.java
          |    |    |    |    |    RouteController.java
          |    |    |    |    |    ...
          |    |    |    |
          |    |    |    |___ dtos/
          |    |    |    |    |    ...
          |    |    |    |
          |    |    |    |___ entities/
          |    |    |    |    |    ...
          |    |    |    |
          |    |    |    |___ repositories/
          |    |    |    |    |    ...
          |    |    |    |
          |    |    |    |___ services/
          |    |    |    |    |    ...
          |    |    |    |
          |    |    |    |    SpringTemplateApplication.java
          |    |    resources/
          |    |    |___ META-INF/
          |    |    |    |    mime.type
          |    |    |    
          |    |    |___ static/
          |    |    |    |    ...
          |    |    |    
          |    |    |___ templates/
          |    |    |    |    ...
          |    |    |    
          |    |    |    application.properties
          |
          |___ test/
               |___ java/
                    |___ it.polito.det.template
                         |    SpringTemplateApplicationTests.java
```

 

## Setup

After downloading this template, or after initializing a repository starting from this template, you **need** to perform the following steps.



### Set a LICENSE

Decide which license you want to use



### Modify the package.json

In particular you **need** to modify/set the following attributes of the [package.json](package.json):

* name
* version [OPTIONAL]
* description
* author
* contributors [OPTIONAL]
* license

You also **need** to modify/set those attributes in [client/package.json](client/package.json).

You need then to modify the following settings in the [server/pom.xml](server/pom.xml):

* groupId
* artifactId
* version
* description
* properties
  * start-class



### Install the dependencies

You can install all the dependencies by running the [install](#install) script in the root folder of the project. 

```bash
npm install
```

This will take a while.



### Open the application

Open the *server* with **IntelliJ IDEA Community** and the *client* with **VS Code**.



### Start the application

You then can start the default application by running the [start](#start) script in the root folder of the project. 

```bash
npm start
```

By default the application can will only use HTTP and is available at the following url: [http://localhost:8080/](http://localhost:8000/), but you can modify the default behavior by setting some specific environment variables or by modifying properties in the [application.properties](server/src/main/resources/application.properties).



### Modify eslint settings [OPTIONAL]

In the application you can find 2 different eslint configurations, one in the root: [.eslintrc.js](.eslintrc.js) and one in the client: [client/.eslintrc.js](client/.eslintrc.js). While the one in the client has been created by the vue cli, the other one is custom created and takes advantage of the prettier plugin to format the code. If you wish to modify those configuration please take a look at [Eslint](https://eslint.org/docs/rules/) and [Prettier](https://prettier.io/docs/en/options.html) documentations.



## Client

[Client](client/README.md)



## Server

[Server](server/README.md)



## Documentation

[Documentation](documentation/README.md)



## Docker

In the root directory you also find a [Dockerfile](Dockerfile) which can is used by all the [docker](#docker-1) scripts and it is used to containerize the application. The Dockerfile is written for development, so it will start a HTTP server. If you want to use it for production, please take a look at the Spring docs to understand how to configure it to use only HTTPS. 



## Scripts

In the [package.json](package.json) are defined some useful script. Some of this script just call a series of commands in the right order, concatenating them through &&, while other are some custom scripts which are implemented in the **scripts** directory.

To list all the available scripts you can run

```bash
npm run
```



### Install

```bash
npm install
```

By running this script in the root folder of the project it will take care of installing the dependencies of the **root**,  the **client** and the **server**. It will create a *node_modules* folder in the **root** and the **client** and it will install all the maven dependencies of the **server** folder (**Required CMD**: `mvn`).



### Start

```bash
npm start
```

This script will take care of **building (only if necessary)  the Vue3 application** and **store the built files** in *server/src/main/resources/static*. Then it will build the server and start it. The server is accessible at http://localhost:8080 (**Required CMD**: `mvn`)



### Build 

```bash
npm run build
```

This script will take care of **building (only if necessary)  the Vue3 application** and **store the built files** in *server/src/main/resources/static*. Then it will build the server. (**Required CMD**: `mvn`)



### Lint

#### Lint

```bash
npm run lint
```

This script will take care of linting the **client** code, without fixing any issue.



#### Fix

```bash
npm run fix
```

This script instead will take care of linting the **client** code and fix **ANY** issue it is able to auto fix.



### Lint local

#### Lint

```bash
npm run lint-local
```

This script will take care of linting the **scripts** folder



#### Fix

```bash
npm run fix
```

This script will take care of linting the **scripts** folder and fix **ANY** issue it is able to auto fix.



### Docker

#### Build

```bash
npm run docker-build
```

This script will take care of building the application into a Docker image (**Required CMD**: `docker`)



#### Run

```bash
npm run docker-run
```

The script will take care of creating a container from the previously built Docker image exposing only the HTTP server. This can be useful during development, to test it without the need to create a ssl certificate. The container will be exposed on port 8080, but you can modify that value by changing the `localPort` variable of the [scripts/docker-run-dev.js](scripts/docker-run-dev.js) file (**Required CMD**: `docker`)



#### Stop

```bash
npm run docker-stop
```

The script will take care of stopping the Docker container (**Required CMD**: `docker`)



#### Start

```bash
npm run docker-start
```

The script will take care of starting the Docker container (**Required CMD**: `docker`)



#### Rm

```bash
npm run docker-rm
```

This script will take care of deleting the Docker container created by [docker-run ](#run) (**Required CMD**: `docker`)



#### Rmi

```bash
npm run docker-rm
```

This script will take care of deleting the Docker image created by [docker-build](#build) and all the child containers (**Required CMD**: `docker`)



## Useful commands

If you want to generate the ssl certificate in order to test the application over ssl you can create a self signed certificate with the following series of commands:

```bash
mkdir server/ssl
openssl req -new -x509 -days 365 -nodes -out server/ssl/ssl.cert -keyout server/ssl/ssl.key
chmod 600 server/ssl/ssl.*
```

