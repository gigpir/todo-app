package it.polito.det.springTemplate;

import it.polito.det.springTemplate.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@SpringBootApplication
public class SpringTemplateApplication {

	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

		return modelMapper;
	}

	@Bean
	@Transactional
	CommandLineRunner runner(
			@Autowired PasswordEncoder passwordEncoder,
			@Autowired UserRepository userRepository
			) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

			}
		};
	}

	public static void main(String[] args) {
		System.out.println();
		SpringApplication.run(SpringTemplateApplication.class, args);
	}
}
