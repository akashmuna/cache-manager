FROM openjdk:8-jdk-slim
MAINTAINER Akash Mohapatra <akash.zlatan@gmail.com>
ADD target/docker-zuulservice.jar docker-zuulservice.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","docker-zuulservice.jar"]