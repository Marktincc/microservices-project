package com.example.servicescustomers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.example.servicescustomers",
        "com.example.commonutils"

})
public class ServiceCustomersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCustomersApplication.class, args);
    }

}
