package com.jkaszczynski.service.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotesServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesServicesApplication.class, args);
    }

}
