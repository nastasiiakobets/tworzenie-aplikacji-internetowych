package com.kpiweb.weblabs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@CrossOrigin
public class WeblabsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeblabsApplication.class, args);
    }


}
