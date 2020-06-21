#!/usr/bin/env bash

./mvnw package
sudo docker-compose up --build