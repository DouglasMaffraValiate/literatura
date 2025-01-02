package com.douglasmv.literatura;

import com.douglasmv.literatura.main.Main;
import com.douglasmv.literatura.repository.AutorRepository;
import com.douglasmv.literatura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner{

	private LivroRepository livroRepository;
	private AutorRepository autorRepository;

	@Autowired
	public LiteraturaApplication(LivroRepository livroRepository, AutorRepository autorRepository) {
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(livroRepository, autorRepository);
		main.rodarAplicacao();
	}
}
