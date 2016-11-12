package com.akijay.retailstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@EnableSwagger2
//Above and below for swagger
@ComponentScan(basePackageClasses= {
        RetailStoreApplication.class
})
public class RetailStoreApplication {

    public static void main(String[] args) throws Exception {
            SpringApplication.run(RetailStoreApplication.class, args);
    }

    @Bean
    public Docket retailStoreApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.akijay.retailstore.controllers"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());

        //See the API documentation for usage of other configurations

    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Retail Store REST API",
                "REST API for managing a chain of retail stores",
                "1.0",
                "about.html",
                new Contact("Vijay and Akila", "about.html", "vijaykumar.v@gmail.com"),
                "AKIJAY LICENSE",
                "about.html"
                );
        return apiInfo;
    }
}
