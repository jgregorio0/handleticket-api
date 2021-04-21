#!/bin/bash
cd /home/jgregorio/projects/handleticket-api/env

mvn -f ../pom.xml clean package
cp ../target/handleticket-api-1.0.jar ./docker-handleticket-api/handleticket-api.jar


sudo docker container ls -a
sudo docker container rm htapi

sudo docker images
sudo docker image rm handleticket-api:1.0

sudo docker build -t handleticket-api:1.0 ./docker-handleticket-api/.

sudo docker-compose -f ./docker-compose/docker-compose.yml up