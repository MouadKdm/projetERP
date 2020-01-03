package api.erp.banqueservice;

import api.erp.banqueservice.model.Agence;
import api.erp.banqueservice.model.Agent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BanqueServiceApplication implements CommandLineRunner {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(BanqueServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Agent agent = new Agent();
	}
}
