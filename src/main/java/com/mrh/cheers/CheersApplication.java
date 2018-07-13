package com.mrh.cheers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableScheduling
@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class CheersApplication {

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World!";
	}


	public static void main(String[] args) {
		SpringApplication.run(CheersApplication.class, args);
	}
}
