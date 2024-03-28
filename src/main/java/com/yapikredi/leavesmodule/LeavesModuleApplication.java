package com.yapikredi.leavesmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication()
@EnableScheduling
public class LeavesModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeavesModuleApplication.class, args);
	}

}
