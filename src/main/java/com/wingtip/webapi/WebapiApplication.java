package com.wingtip.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebapiApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(WebapiApplication.class, args);
		}catch(Exception e) {
			if(e.getClass().getName().contains("SilentExitException")) {
	            System.out.println("Spring is restarting the main thread - See spring-boot-devtools");
	        } else {
	            e.printStackTrace();
	        }
		}
	}

}
