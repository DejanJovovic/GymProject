package ftn.com.mojaTeretana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MojaTeretanaApplication extends SpringBootServletInitializer {
	
	
	protected SpringApplicationBuilder configyre(SpringApplicationBuilder application) {
		return application.sources(MojaTeretanaApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MojaTeretanaApplication.class, args);
	}
}

