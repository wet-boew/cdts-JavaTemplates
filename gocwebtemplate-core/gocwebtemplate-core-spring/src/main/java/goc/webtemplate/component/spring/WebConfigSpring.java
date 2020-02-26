package goc.webtemplate.component.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * To enable the GocWebTemplate in your spring boot application, the following must be added to your Application class:
 * 
 * import goc.webtemplate.component.spring.WebConfigSpring;
 * {@literal @}Import(WebConfigSpring.class)
 *
 */
@Configuration
@ComponentScan
public class WebConfigSpring {
	
}



