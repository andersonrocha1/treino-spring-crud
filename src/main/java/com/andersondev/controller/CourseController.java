package com.andersondev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andersondev.model.CourseModel;
import com.andersondev.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {

		this.courseService = courseService;
	}

	@GetMapping
	public @ResponseBody List<CourseModel> list() {

		return courseService.list();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CourseModel create(@RequestBody @Valid CourseModel course) {

		return courseService.create(course);
		// return
		// ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));

	}

	@GetMapping("/{id}")
	public CourseModel findById(@PathVariable @NotNull @Positive Long id) {

		return courseService.findById(id);
	}

	@PutMapping("/{id}")
	public CourseModel update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid CourseModel course) {

		return courseService.update(id, course);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotNull @Positive Long id) {

		courseService.delete(id);

	}

}
