package com.springbootlearn.damo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.*;

@SpringBootApplication
/*@Configuration
@ComponentScan
@EnableAutoConfiguration*/
public class DamoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DamoApplication.class, args);
    }
}
