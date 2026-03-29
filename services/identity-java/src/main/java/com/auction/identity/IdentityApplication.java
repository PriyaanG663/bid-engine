package com.auction.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class IdentityApplication {

    @PostConstruct
    public void init() {
        // Forces the JVM to ignore the "Calcutta" system setting
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
        System.out.println("🚀 IDENTITY SERVICE IS ONLINE!");
    }
}
