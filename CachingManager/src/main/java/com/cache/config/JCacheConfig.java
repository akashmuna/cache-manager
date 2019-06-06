package com.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import java.time.Duration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableCaching
public class JCacheConfig{
    
       public static final String NEWS_CACHE = "newsCache";
       
       @Autowired
       private Environment env;
    
       @Bean
       public LettuceConnectionFactory redisConnectionFactory() {
    	RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
    	redisConf.setHostName(env.getProperty("spring.redis.host"));
    	redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
    	redisConf.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));	    
            return new LettuceConnectionFactory(redisConf);
       }
       
       @Bean
       public RedisCacheConfiguration cacheConfiguration() {
    	RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
    	  .entryTtl(Duration.ofMinutes(Integer.parseInt(env.getProperty("spring.cache.redis.time-to-live"))))
    	  .disableCachingNullValues();	
    	return cacheConfig;
       }
       
       @Bean
       public RedisCacheManager cacheManager() {
    	RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory())
    	  .cacheDefaults(cacheConfiguration())
    	  .transactionAware()
    	  .build();
    	return rcm;
       } 
 
}
