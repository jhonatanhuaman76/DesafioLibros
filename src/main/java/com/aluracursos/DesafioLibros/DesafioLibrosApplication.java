package com.aluracursos.DesafioLibros;

import com.aluracursos.DesafioLibros.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLibrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
	}
}
