FROM openjdk:8-jdk-slim
MAINTAINER Akash Mohapatra <akash.zlatan@gmail.com>
ADD target/docker-cachingmanager.jar docker-cachingmanager.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","docker-cachingmanager.jar"]