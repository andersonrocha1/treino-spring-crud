package com.andersondev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.andersondev.dto.CourseDTO;
import com.andersondev.dto.CoursePageDTO;
import com.andersondev.dto.mapper.CourseMapper;
import com.andersondev.exceptions.RecordNotFoundException;
import com.andersondev.model.CourseModel;
import com.andersondev.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {

		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	public CoursePageDTO list(@PositiveOrZero int pageNumber, 
	@Positive @Max(100) int pageSize) {
		
		Page<CourseModel> pages = courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
		List<CourseDTO> courses = pages.get().map(courseMapper::toDTO).collect(Collectors.toList());

		return new CoursePageDTO(courses, pages.getTotalElements(), pages.getTotalPages()) ;
				/* 
				.stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
				*/
	}

	public CourseDTO findById(@NotNull @Positive Long id) {

		return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));

	}

	public CourseDTO create(@Valid @NotNull CourseDTO course) {

		return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));

	}

	public CourseDTO update(@NotNull @Positive Long id, @Valid CourseDTO courseDto) {

		return courseRepository.findById(id).map(recordNotFound -> {
			CourseModel course = courseMapper.toEntity(courseDto);
			recordNotFound.setName(courseDto.name());
			recordNotFound.setCategory(this.courseMapper.convertCategoryValue(courseDto.category()));
			recordNotFound.getLessons().clear();
			course.getLessons().forEach(recordNotFound.getLessons()::add);
			
			return courseMapper.toDTO(courseRepository.save(recordNotFound));

		}).orElseThrow(() -> new RecordNotFoundException(id));

	}

	public void delete(@NotNull @Positive Long id) {
		
		courseRepository.delete(courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));

		}

	
}
