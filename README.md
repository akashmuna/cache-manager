# cache-manager

cache-manager is a Spring Secured MicroService + Redis Cache implemented application which helps in holding up the response in Redis Caching system for a specified time period after being authorized by the Eureka Gateway Using CachingManagerAuthToken application.

The Application uses Netflix OSS eureka gateway to act as an API Gateway for connecting to other microservices.

## CachingManager

CachingManager is a Spring MVC + Redis CacheManager implemented application which helps in holding up the response in Redis Caching system for a specified time period.

## CachingManagerAuthToken

This is Spring secured application which generates a JSON Web-Based Taken (JWT) Token after authenticating the user.

