package adam.dev.liveCode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LiveCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveCodeApplication.class, args);
	}

}
