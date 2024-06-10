package com.bankaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class PokeProxyAPI {
    public static void main(String[] args) {
        SpringApplication.run(PokeProxyAPI.class, args);
    }
}
