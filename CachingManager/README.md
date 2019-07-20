# cache-manager

CachingManager is a Token Based Spring Secured MVC + Redis CacheManager implemented application which helps in holding up the response in Redis Caching system for a specified time period. CachingManager Application shares the list of headlines of many of the news channel after authorizing the user using Spring Security microservice (CachingManagerAuthToken)

Redis is an open source (BSD licensed), in-memory data structure store, used as a database, cache and message broker. It supports data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs, geospatial indexes with radius queries and streams. Redis has built-in replication, Lua scripting, LRU eviction, transactions and different levels of on-disk persistence, and provides high availability via Redis Sentinel and automatic partitioning with Redis Cluster.

The Project retrieves the current headlines and stores them in a Redis Cache

### PreRequisites :

1. Installing Redis on your Local System <br/>

   Follow the [Redis Guide](https://redis.io/download) to Install the Redis IO on your Local System. <br/>

2. Getting an API key from [NewsAPI](https://newsapi.org/docs/get-started) use it in application.properties File.

### Solution Architecture 

![Imgurl](https://i.imgur.com/rikeU19.jpg)

Highlights :
* Token Based Authorization (Need to Pass the Token Generated using CachingManagerAuthToken)
* Caching mechanism to speed up responses
* Uses Redis IO caching mechanism to speed up your responses.
* Uses [NewsAPI](https://newsapi.org) to Fetch the top-headline of the day.

#### Note:
Installation of Redis Component is compulsory for this project to work.

### Endpoints

localhost:8081/NewsAPI/HeadLines?newschannel=cnn - News Headlines Service <br />
[Sources](https://newsapi.org/sources) can be found in News API which needs to be passed in the parameter "newschannel"

Examples <br />
BBC Sport : bbc-sport <br />
ESPN Cric Info : espn-cric-info

### Sample Calls

Ex 1.: Getting CNN News Headlines (Powered by [NewsAPI](https://newsapi.org))
```bash
curl -X POST \
  'http://localhost:9091/newsapi/NewsAPI/HeadLines?newschannel=cnn' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2MDg3MDQyMSwiaWF0IjoxNTYwODUyNDIxfQ.zsOw_MKiZdTreWrdlcKhw0fFS8fYplfzJ6n2kPzv0juat8dOPVrv_CiWoUBb0Bvn4S8U40Jqq24jIdBI6zZH_g' \
  -H 'Content-Type: application/json' \
```
Sample response:
```json
[
    {
        "source": {
            "id": "cnn",
            "name": "CNN"
        },
        "author": null,
        "title": "Lawmaker: Trump has failed on his policies at the border - CNN Video",
        "description": "In an interview with CNN's Wolf Blitzer, Rep. David Cicilline (D-RI) criticized President Trump's border security policies and reacted to Jake Tapper's reporting that Trump privately told border agents to not admit migrants, according to two sources.",
        "url": "http://us.cnn.com/videos/politics/2019/04/08/cicilline-trump-border-security-policies-wolf-blitzer-tsr-sot-vpx.cnn",
        "urlToImage": "https://cdn.cnn.com/cnnnext/dam/assets/190408184040-david-cicilline-super-tease.jpg",
        "publishedAt": "2019-04-09T05:38:55.7220941Z",
        "content": "Chat with us in Facebook Messenger. Find out what's happening in the world as it unfolds."
    },
    {
        "source": {
            "id": "cnn",
            "name": "CNN"
        },
        "author": null,
        "title": "Cuomo and Lemon critique Trump's 'un-American' message - CNN Video",
        "description": "CNN's Chris Cuomo and Don Lemon discuss President Trump's push for a more widespread family separation policy at the US-Mexico border, and denounce the harsh rhetoric directed toward asylum seekers.",
        "url": "http://us.cnn.com/videos/politics/2019/04/09/cuomo-lemon-handoff-trump-border-security-sot-cpt-vpx.cnn",
        "urlToImage": "https://cdn.cnn.com/cnnnext/dam/assets/190408223000-cuomo-lemon-split-super-tease.jpg",
        "publishedAt": "2019-04-09T05:38:49.4096362Z",
        "content": "Chat with us in Facebook Messenger. Find out what's happening in the world as it unfolds."
    },
    {
        "source": {
            "id": "cnn",
            "name": "CNN"
        },
        "author": null,
        "title": "Ex-ICE head: Trump had 'single dumbest idea I've ever heard' - CNN Video",
        "description": "Former Acting Director of US Immigration and Customs Enforcement John Sandweg says President Trump's suggestion to eliminate immigration judges is \"the single dumbest idea I've ever heard\" in terms of dealing with border crossings.",
        "url": "http://us.cnn.com/videos/politics/2019/04/08/former-ice-head-john-sandweg-trump-immigration-judges-dumbest-idea-sot-newday-vpx.cnn",
        "urlToImage": "https://cdn.cnn.com/cnnnext/dam/assets/190408084940-former-ice-head-john-sandweg-trump-immigration-judges-dumbest-idea-sot-newday-vpx-00000000-super-tease.jpg",
        "publishedAt": "2019-04-09T05:38:37.6727181Z",
        "content": "Chat with us in Facebook Messenger. Find out what's happening in the world as it unfolds."
    },
    {
        "source": {
            "id": "cnn",
            "name": "CNN"
        },
        "author": "Analysis by Stephen Collinson, CNN",
        "title": "The law or the President: The Trump appointees' dilemma",
        "description": "Sooner or later, many of the President's subordinates face the same dilemma.",
        "url": "http://us.cnn.com/2019/04/09/politics/donald-trump-kirstjen-nielsen-immigration/index.html",
        "urlToImage": "https://cdn.cnn.com/cnnnext/dam/assets/190405115039-01-donald-trump-04052019-super-tease.jpg",
        "publishedAt": "2019-04-09T05:03:17Z",
        "content": null
    },
]
```
