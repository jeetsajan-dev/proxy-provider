package com.proxy_provider.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ProxyProviderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyProviderDemoApplication.class, args);
	}

}
