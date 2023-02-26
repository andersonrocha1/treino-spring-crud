package com.andersondev.dto.mapper;

import org.springframework.stereotype.Component;

import com.andersondev.dto.CourseDTO;
import com.andersondev.model.CourseModel;

@Component
public class CourseMapper {
	
	public CourseDTO toDTO(CourseModel course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public CourseModel toEntity(CourseDTO courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        CourseModel course = new CourseModel();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        course.setStatus("Ativo");
        return course;
    }

}
