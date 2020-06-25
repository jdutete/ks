package com.jdutete.ks.springcamel;

import org.apache.camel.zipkin.starter.CamelZipkin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@CamelZipkin
public class SpringCamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCamelApplication.class, args);
	}

}
