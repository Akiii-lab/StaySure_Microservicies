package StaySure.demoMicroservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "StaySure")
public class StaySureApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaySureApplication.class, args);
	}

}
