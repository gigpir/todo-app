#!/bin/bash

# Load environment file
if [ -f .env ]
then
  export $(cat .env | sed 's/#.*//g' | xargs)
else
  echo 'Please create a .env file where you define the DB_NAME and DB_PASSWORD environment variables'
  exit 1;
fi

# Container name (same as Database name, for simplicity)
name=${DB_NAME}

# Database password
password=${DB_PASSWORD}

# Database public port
port=${DB_PORT}

# Local dir (./db)
dir="$(pwd)/db"

# Create local dir
if ! [ -d "$dir" ]; then
  # The directory does not exist
  echo "Creating directory ${dir}"
  mkdir "${dir}"
else
  echo "Directory ${dir} already exists [SKIPPED]"
fi

# Pull mariadb image
docker pull mariadb

# Delete previous container if it exist
if [ "$(docker ps -a | grep ${name})" ];
then
  docker stop "${name}"
  docker rm "${name}"
fi

# Run container
docker run --name "${name}" -v "${dir}:/var/lib/mysql" -p ${port}:3306 -e MYSQL_ROOT_PASSWORD=${password} -d mariadb:latest

echo "Username: root"
echo "Password: ${password}"
