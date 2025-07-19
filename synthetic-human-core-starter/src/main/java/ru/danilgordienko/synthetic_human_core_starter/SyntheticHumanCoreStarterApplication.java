package ru.danilgordienko.synthetic_human_core_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SyntheticHumanCoreStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyntheticHumanCoreStarterApplication.class, args);
	}

}
