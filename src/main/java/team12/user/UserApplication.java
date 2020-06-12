package team12.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
public class UserApplication {

    	public static void main(String[] args) {
    		SpringApplication.run(UserApplication.class, args);
    	}
          
  @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
