package pl.wszib.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.wszib.edu.DAO.AccountDAO;
import pl.wszib.edu.model.Account;

@SpringBootApplication
public class Application {
	private static AccountDAO accountDAO;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



}
