package gr.di.uoa.m151;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class M151ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(M151ApiApplication.class, args);
    }

}
