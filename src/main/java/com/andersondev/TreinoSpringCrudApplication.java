package com.andersondev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.andersondev.model.CourseModel;
import com.andersondev.repository.CourseRepository;

@SpringBootApplication
public class TreinoSpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreinoSpringCrudApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			
			CourseModel curso = new CourseModel();
			
			curso.setName("Angular 15");
			curso.setCategory("Front-End");
			
			courseRepository.save(curso);
		};
	}

}
