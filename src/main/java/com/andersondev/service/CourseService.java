package com.andersondev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.andersondev.exceptions.RecordNotFoundException;
import com.andersondev.model.CourseModel;
import com.andersondev.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {

		this.courseRepository = courseRepository;
	}

	public List<CourseModel> list() {

		return courseRepository.findAll();
	}

	public CourseModel findById(@PathVariable @NotNull @Positive Long id) {

		return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));

	}

	public CourseModel create(@Valid CourseModel course) {

		return courseRepository.save(course);

	}

	public CourseModel update(@NotNull @Positive Long id, @Valid CourseModel course) {

		return courseRepository.findById(id).map(recordNotFound -> {

			recordNotFound.setName(course.getName());
			recordNotFound.setCategory(course.getCategory());
			return courseRepository.save(recordNotFound);

		}).orElseThrow(() -> new RecordNotFoundException(id));

	}

	public void delete(@PathVariable @NotNull @Positive Long id) {
		
		courseRepository.delete(courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));

		}

	
}
