package Sicredi.Teste;

import org.springframework.boot.SpringApplication;

public class TestTesteApplication {

	public static void main(String[] args) {
		SpringApplication.from(TesteApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
