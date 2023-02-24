package com.andersondev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andersondev.model.CourseModel;
import com.andersondev.repository.CourseRepository;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
	
	
	private final CourseRepository courseRepository;
	
	@GetMapping
	public List<CourseModel> list(){
		
		return courseRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CourseModel create(@RequestBody CourseModel course) {
		//Optei por usar a anotação em vez do ResponseEntity	
		return courseRepository.save(course);
		//return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseModel> findById(@PathVariable Long id) {
		
		return courseRepository.findById(id)
				.map(recordNotFound -> ResponseEntity.ok().body(recordNotFound))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CourseModel> update(@PathVariable Long id, @RequestBody CourseModel course){
		
		return courseRepository.findById(id)
				.map(recordNotFound -> {
					
					recordNotFound.setName(course.getName());
					recordNotFound.setCategory(course.getCategory());
					CourseModel updated = courseRepository.save(recordNotFound);
					return ResponseEntity.ok().body(updated);
					
				})
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		
		return courseRepository.findById(id)
				.map(recordNotFound -> {
					courseRepository.deleteById(id);
					return ResponseEntity.noContent().<Void>build();					
				})
				.orElse(ResponseEntity.notFound().build());
		
	}
	
}
