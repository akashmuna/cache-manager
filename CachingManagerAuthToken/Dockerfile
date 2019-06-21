FROM openjdk:8-jdk-slim
MAINTAINER Akash Mohapatra <akash.zlatan@gmail.com>
ADD target/docker-cachingmanagerauthtoken.jar docker-cachingmanagerauthtoken.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","docker-cachingmanagerauthtoken.jar"]