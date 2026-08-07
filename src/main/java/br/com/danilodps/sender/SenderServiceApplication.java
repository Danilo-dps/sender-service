package br.com.danilodps.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableRetry
@SpringBootApplication
public class SenderServiceApplication {

	private SenderServiceApplication(){}

	static void main(String[] args) {
		SpringApplication.run(SenderServiceApplication.class, args);
	}

}
