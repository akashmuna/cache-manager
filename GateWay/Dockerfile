FROM openjdk:8-jdk-slim
MAINTAINER Akash Mohapatra <akash.zlatan@gmail.com>
ADD target/docker-gateway.jar docker-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","docker-gateway.jar"]