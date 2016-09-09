package com.calypso.yahooFinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * @author jayati
 * 
 * This class initialises spring-boot.
 * @SpringBootApplication is equivalent to @Configuration @EnableAutoConfiguration @ComponentScan 
 *
 */
@SpringBootApplication
public class YahooFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YahooFinanceApplication.class, args);
	}
}
