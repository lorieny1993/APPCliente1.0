package lorieny.com.br.App_Cadastro_Cliente;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class AppCadastroClienteApplication {
	private static Logger logger = LoggerFactory.getLogger(AppCadastroClienteApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando api de Cadastro de Clientes");
		SpringApplication.run(AppCadastroClienteApplication.class, args);
		logger.info("Api de cadastro de Clientes pronta para receber requisições");
	}

}
