package br.com.lucrolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.lucrolivre", "br.com.lucrolivre.controller"})
public class LucroLivreApplication {
    public static void main(String[] args) {
        SpringApplication.run(LucroLivreApplication.class, args);
    }
}