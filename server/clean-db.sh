#!/bin/bash

# Load environment file
if [ -f .env ]
then
  export $(cat .env | sed 's/#.*//g' | xargs)
else
  echo 'Please create a .env file where you define the DB_NAME and DB_PASSWORD environment variables'
  exit 1;
fi

# Database name
name=${DB_NAME}

# Delete previous container if it exist
if [ "$(docker ps -a | grep ${name})" ];
then
  docker stop "${name}"
  docker rm "${name}"
fi
