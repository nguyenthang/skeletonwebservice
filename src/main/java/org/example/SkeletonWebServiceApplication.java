package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableAsync
public class SkeletonWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkeletonWebServiceApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager(){
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("products");

		return cacheManager;
	}
}
