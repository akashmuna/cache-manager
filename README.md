# cache-manager

CachingManager is a Spring MVC + CacheManager implemented application which helps in holding up the response in a Spring's central cache manager SPI for a specified time period.

### How to run the service:
1. Git Clone 
    ```bash
    https://github.com/akashmuna/cache-manager.git
    ```
2. Maven build
    ```bash
    mvn clean install
    ```
### Solution Architecture 

![Imgurl](https://imgur.com/0QfDg17.jpg)

Highlights :
* Caching mechanism to speed up responses
* Uses [NewsAPI](https://newsapi.org) to Fetch the top-headline of the day.

#### Note:
This is by no means a sophisticated CacheManager; it comes with no cache configuration options. However, it may be useful for testing or simple caching scenarios. For advanced local caching needs, consider JCacheCacheManager, EhCacheCacheManager, CaffeineCacheManager.

### Endpoints

http://localhost:8080/ - News Headlines Service

### Sample Calls

Ex 1.: Getting CNN News Headlines (Powered by [NewsAPI](https://newsapi.org))
```bash
localhost:8080/NewsAPI/HeadLines
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
