# cache-manager

cache-manager is a Spring Secured MicroService + Redis Cache implemented application which helps in holding up the response in Redis Caching system for a specified time period after being authorized by the Eureka Gateway Using CachingManagerAuthToken application.

The Application uses Netflix OSS eureka gateway to act as an API Gateway for connecting to other microservices.

## GateWay

Eureka Server which acts single point of connect which has to be highly available as every service communicates it to discover other services.

Note : As this the single point of communication, this needs to be Load-balanced in Production setup

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
    
2. Get an API key from [NewsAPI](https://newsapi.org/docs/get-started) use it in application.properties file for CachingManager.

	```bash
	app.NEWS_API_KEY=
	```

3. Maven build

    ```bash
    mvn clean install
	```
	
4. Run docker-compose

	```bash
	docker-compose up
	```
	
### Endpoints

Local System EndPoints:

	http://localhost:8080/ - Eureka Discovery Service

	http://localhost:9091/ - Zuul Proxy Gateway

Docker EndPoints:

	http://eureka-gatewayserver:8080/ - Eureka Discovery Service

	http://eureka-gatewayserver:9091/ - Zuul Proxy Gateway

### API EndPoints

Both the shared services support POST method while retrieving the news, sports headlines and this would request for a JWT Token to share the response. 

Local System EndPoints:

	http://localhost:9091/authenticate  - CachingManagerAuthToken

	http://localhost:9091/newsapi/NewsAPI/HeadLines?newschannel=cnn  - CachingManager

Docker EndPoints:

	http://eureka-gatewayserver:9091/authenticate  - CachingManagerAuthToken

	http://eureka-gatewayserver:9091/newsapi/NewsAPI/HeadLines?newschannel=cnn  - CachingManager
	
Go through the README file for each individual service CachingManagerAuthToken and CachingManager to view the request format. 