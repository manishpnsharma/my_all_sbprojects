package com.cacheable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheableApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheableApplication.class, args);
	}

}
