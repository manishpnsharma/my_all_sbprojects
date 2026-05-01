package com.spring.cloude.config.server.spring_cloude_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudeConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudeConfigServerApplication.class, args);
	}

}
