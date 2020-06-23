package com.ms.basicsecurity;

import com.ms.basicsecurity.entities.UserAccount;
import com.ms.basicsecurity.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedDatabase(UserRepository userRepository){
		return args -> Stream.of("test,{noop}test","sam,{noop}sam")
				             .map(u->u.split(","))
				             .forEach(t->userRepository.save(new UserAccount(t[0],t[1],true)));
	}

}
