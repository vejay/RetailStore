package com.akijay.retailstore.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@EnableAutoConfiguration
public class Example 
{
	//@RequestMapping("/")
	String home()
	{
		return "Hello World21!";
	}
	
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(Example.class, args);
	}
}
