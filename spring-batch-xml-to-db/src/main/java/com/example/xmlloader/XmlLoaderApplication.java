package com.example.xmlloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class XmlLoaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlLoaderApplication.class, args);
    }

}
