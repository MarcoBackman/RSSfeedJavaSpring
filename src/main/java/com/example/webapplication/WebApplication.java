package com.example.webapplication;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class WebApplication {
	
	//feed object singleton instance
	private static RSSFeed feedObject;
	
	public static void main(String[] args) {
		//create singleton
		if (feedObject == null) {
			feedObject = new RSSFeed();
		}
		SpringApplication.run(WebApplication.class, args);
	}
	
	@GetMapping(path = "/")
	public String home() {
		return "Hi!";
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(path = "/rss")
	public String rss() throws IOException {		
	    return feedObject.getContent();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
