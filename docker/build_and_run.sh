#!/bin/sh
mvn clean package && docker build -f docker/Dockerfile.jvm -t quarkus/admin-starter . && docker run -i --rm -p 8080:8080 quarkus/admin-starter