package com.citizen.config;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class JCacheConfig {
	
	public JCacheManagerCustomizer cacheCustomizer() {
		return new JCacheManagerCustomizer() {
			
			@Override
			public void customize(CacheManager cacheManager) {
				MutableConfiguration<Object, Object> config = new MutableConfiguration<Object, Object>();
				config.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.FIVE_MINUTES));
				config.setStatisticsEnabled(true);
				cacheManager.createCache("articleCache", config);
				
			}
		};
	}

}
