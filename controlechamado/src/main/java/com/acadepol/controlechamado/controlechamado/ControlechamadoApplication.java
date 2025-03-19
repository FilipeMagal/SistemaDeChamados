package com.acadepol.controlechamado.controlechamado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ControlechamadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlechamadoApplication.class, args);

		String senha = "filipe123";  // Substitua pela senha do administrador

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(senha);  // Criptografa a senha

		System.out.println("Senha criptografada: " + senhaCriptografada);  // Exibe a senha criptografada
	}


}


