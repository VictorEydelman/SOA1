package se.ifmo.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
public class MainServiceSpringApplication  {

	public static void main(String[] args) {
        //new MainServiceSpringApplication().configure(new SpringApplicationBuilder(MainServiceSpringApplication.class)).run(args);
		SpringApplication.run(MainServiceSpringApplication.class, args);
	}

}
