package fr.hovedopgave.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        // Manually encoding passwords
        String password1 = passwordEncoder.encode("password");

		System.out.println("Encrypted password for 'password': " + password1);

		SpringApplication.run(DemoApplication.class, args);
	}

}
