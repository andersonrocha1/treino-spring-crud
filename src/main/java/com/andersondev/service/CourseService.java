package com.andersondev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

	public Optional<CourseModel> findById(@PathVariable @NotNull @Positive Long id) {

		return courseRepository.findById(id);

	}

	public CourseModel create(@Valid CourseModel course) {

		return courseRepository.save(course);

	}

	public Optional<CourseModel> update(@NotNull @Positive Long id, @Valid CourseModel course) {

		return courseRepository.findById(id).map(recordNotFound -> {

			recordNotFound.setName(course.getName());
			recordNotFound.setCategory(course.getCategory());
			return courseRepository.save(recordNotFound);

		});

	}

	public boolean delete(@PathVariable @NotNull @Positive Long id) {

		return courseRepository.findById(id).map(recordNotFound -> {
			courseRepository.deleteById(id);
			return true;
		}).orElse(false);

	}

}
