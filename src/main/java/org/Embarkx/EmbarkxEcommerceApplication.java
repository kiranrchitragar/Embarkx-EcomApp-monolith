package org.Embarkx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbarkxEcommerceApplication {

	public static void main(String[] args) {

		System.out.println(java.time.ZoneId.systemDefault());
		SpringApplication.run(EmbarkxEcommerceApplication.class, args);
	}

}
