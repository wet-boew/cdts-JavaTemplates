package goc.webtemplate.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import goc.webtemplate.component.spring.WebConfigSpring;

@SpringBootApplication
@Import(WebConfigSpring.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
