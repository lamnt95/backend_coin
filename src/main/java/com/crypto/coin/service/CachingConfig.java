package com.crypto.coin.service;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.concurrent.ConcurrentMapCache;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
  public CacheManager cacheManager() {
      SimpleCacheManager cacheManager = new SimpleCacheManager();
      cacheManager.setCaches(Arrays.asList(
              new ConcurrentMapCache("coincache")
	  ));
      return cacheManager;
  }
}
