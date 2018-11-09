package com.springbootlearn.damo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger= LoggerFactory.getLogger(DamoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DamoApplication.class, args);
        logger.info("启动类");
    }
}
