#!/usr/bin/env bash

set -e

echo "Build the project and docker images"
mvn clean package -U -Dmaven.test.skip=true
export DOCKER_IP=$(docker-machine ip $(docker-machine active))
echo "DOCKER_IP is " 
echo $DOCKER_IP
docker-compose down
echo "Start the config service first and wait for it to become available"
docker-compose up -d --build
echo  "Attach to the log output of the cluster"
docker-compose logs
