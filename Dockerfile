# Select the nodejs 14 LTS version
FROM node:14 AS build_client

# Upgrade npm to the latest version
RUN npm i -g npm

# Create app directory
WORKDIR /usr/src/app

# Install client dependencies
WORKDIR /usr/src/app/client
COPY ./client/package*.json ./
RUN npm ci

# Copy server code
WORKDIR /usr/src/app/server
COPY ./server/pom.xml ./
COPY ./server/src ./src

# Build client
WORKDIR /usr/src/app/client
COPY ./client .
RUN npm run build

# Build server
FROM maven:3.8-jdk-11-slim AS build_server
WORKDIR /usr/src/app/server
RUN ls
COPY --from=build_client /usr/src/app/server .
RUN ls
RUN mvn -f pom.xml package -Dmaven.test.skip=true

# Lastly start the application using Java11
FROM openjdk:11-slim
WORKDIR /usr/src/app
COPY --from=build_server /usr/src/app/server/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]