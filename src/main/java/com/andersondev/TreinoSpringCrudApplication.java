package com.andersondev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.andersondev.enums.Category;
import com.andersondev.model.CourseModel;
import com.andersondev.model.Lesson;
import com.andersondev.repository.CourseRepository;

@SpringBootApplication
public class TreinoSpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreinoSpringCrudApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			
			//courseRepository.deleteAll();
			
			for(int i = 0; i<20; i++){

				CourseModel curso = new CourseModel();
				
				curso.setName("Angular 15" + i);
				curso.setCategory(Category.FRONT_END);
				
				Lesson lesson = new Lesson();
				lesson.setName("Introdução ao Angular 15");
				lesson.setYoutubeLink("Lr3d6QhZGlU");
				lesson.setCourse(curso);
				curso.getLessons().add(lesson);
				
				
				courseRepository.save(curso);
			}
		
			
		};
	}

}
