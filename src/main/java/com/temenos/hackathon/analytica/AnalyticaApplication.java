package com.temenos.hackathon.analytica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.temenos.hackathon.analytica"})
@EnableJpaRepositories("com.temenos.hackathon.analytica")
@EntityScan("com.temenos.hackathon.analytica")
@EnableAutoConfiguration
@Configuration
public class AnalyticaApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AnalyticaApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AnalyticaApplication.class, args);
		System.out.println("Application Starts..");
	}

}
