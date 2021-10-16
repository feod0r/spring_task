package com.example.alphabank;

import com.example.alphabank.network.Currency;
import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlphabankApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphabankApplication.class, args);
//        Currency currency = Feign.builder().target(Currency.class, "https://openexchangerates.org/api/");
//        System.out.println(currency.getActual("9a7f3c4e61b44c3d86192057328d8b0a", "USD"));
    }

}
