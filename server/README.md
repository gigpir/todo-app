# Spring - Server

**Spring** server implementation

## Table of contents

* [Project structure](#project-structure)
* [Setup](#setup)
  + [Set a LICENSE](#set-a-license)
  + [Modify the pom.xml](#modify-the-pomxml)
  + [Install the dependencies](#install-the-dependencies)
  + [Start the application](#start-the-application)
* [Documentation](#documentation)
* [Scripts](#scripts)
  + [Install](#install)
  + [Dev](#dev)
  + [Start](#start)
  + [Lint](#lint)
    - [Lint](#lint-1)
    - [Fix](#fix)
* [Environment variables](#environment-variables)

## Project structure

```
server/
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

The [src](src) directory contains the source code you need to develop.



## Setup

Please refer to [../README.md](../README.md#setup) for the details to setup the whole project. In this section I will present the details on how to set up only the server.



### Set a LICENSE

Decide which license you want to use



### Modify the pom.xml

In particular you **need** to modify/set the following attributes of the [pom.xml](pom.xml):

* groupId
* artifactId
* version [OPTIONAL]
* name
* properties
  * start-class: here you need to specify the class which contains the **main** method. You need to modify this only if you rename the class **SpringTemplateApplication** which contains the main method



### Install the dependencies

You can install the dependencies by running

```bash
mvn install
```

This will take a while.



### Start the application

You can start the application in two different ways

You can start it using your IDE (like IntelliJ IDEA)

Or you can use maven.

If you use maven, in order to start it you need to run the following commands in the root folder of the server

```bash
mvn package
java -jar target/${artifactId}-${version}.jar
```



## Documentation

[Server documentation](../documentation/server/README.md)



## Scripts

If you wan to take advantage of some utility scripts refer to the main [../README.md](../README.md)



# Database setup

## Prerequisites

You need to have docker installed on your machine in order to be able to run the scripts



## Initialize the db (docker container)

Run the script

```bash
./init-db.sh
```

to create a db called `todo`.

To connect to that db you can use the following credentials:

* Username: `root`
* Password: `admin`



## Delete the docker container

Run the script

```bash
./clean-db.sh
```

> **NOTE:** if those script are not executable then you can change their privileges with the following command: `chmod +x <scritp-name.sh>`



## Delete the db

To delete all the data present in the db you need to simply delete the [db](db) folder.

You can do so by running the following command in the root directory of the project

```bash
rm -rf db
```

