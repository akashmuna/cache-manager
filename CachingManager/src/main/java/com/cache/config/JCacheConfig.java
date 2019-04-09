package com.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class JCacheConfig{
    
       public static final String ARTICLE_CACHE = "articleCache";
       
       private static final Logger logger = LoggerFactory.getLogger(JCacheConfig.class);
    
       @Bean
    public ConcurrentMapCacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(ARTICLE_CACHE);

        return cacheManager;
    }

    //destroy cache each 1 minute
    @CacheEvict(allEntries = true, value = {ARTICLE_CACHE})
    @Scheduled(fixedDelay = 3 * 60 * 60 * 1000 ,  initialDelay = 500)
    public void reportCacheEvict() {
              logger.debug("Flush Cache");
    }
}
