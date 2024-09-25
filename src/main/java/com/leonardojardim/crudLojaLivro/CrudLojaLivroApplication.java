package com.leonardojardim.crudLojaLivro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CrudLojaLivroApplication {

    public static void main(String[] args) {
        // Carrega o arquivo .env antes de iniciar o Spring Boot
        Dotenv dotenv = Dotenv.load();

        // Define as propriedades do sistema com variáveis minúsculas
        System.setProperty("db_url", dotenv.get("db_url"));
        System.setProperty("db_username", dotenv.get("db_username"));
        System.setProperty("db_password", dotenv.get("db_password"));
        System.setProperty("jwt_secret", dotenv.get("jwt_secret"));

        // Agora inicia a aplicação Spring
        SpringApplication.run(CrudLojaLivroApplication.class, args);
    }
}
