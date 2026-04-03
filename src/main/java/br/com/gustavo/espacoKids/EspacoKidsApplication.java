package br.com.gustavo.espacoKids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EspacoKidsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EspacoKidsApplication.class, args);
	}

}
