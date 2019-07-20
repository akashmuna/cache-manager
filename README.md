# cache-manager

cache-manager is a Spring Secured MicroService + Redis Cache implemented application which helps in holding up the response in Redis Caching system for a specified time period after being authorized by the Eureka Gateway Using CachingManagerAuthToken application.

The Application uses Netflix OSS eureka gateway to act as an API Gateway for connecting to other microservices.

## GateWay

Eureka Server which acts single point of connect which has to be highly available as every service communicates it to discover other services.

## Zuul

Eureka Client which helps to route the request to CachingManager and CachingManagerAuthToken

## CachingManager

CachingManager is a Spring MVC + Redis CacheManager implemented application which helps in holding up the response in Redis Caching system for a specified time period.

## CachingManagerAuthToken

This is Spring secured application which generates a JSON Web-Based Token (JWT) after authenticating the user.

### How to run the service:
1. Git Clone 
    ```bash
    https://github.com/akashmuna/cache-manager.git
    ```
2. Maven build
    ```bash
    mvn clean install
    ```

