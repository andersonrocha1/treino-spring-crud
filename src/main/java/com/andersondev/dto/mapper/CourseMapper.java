package com.andersondev.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.andersondev.dto.CourseDTO;
import com.andersondev.dto.LessonDTO;
import com.andersondev.enums.Category;
import com.andersondev.model.CourseModel;
import com.andersondev.model.Lesson;

@Component
public class CourseMapper {
	
	public CourseDTO toDTO(CourseModel course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessons = course.getLessons()
        		.stream()
        		.map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeLink()))
        		.collect(Collectors.toList());
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
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
        course.setCategory(convertCategoryValue(courseDTO.category()));
        
        List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setYoutubeLink(lessonDTO.youtubeLink());
            lesson.setCourse(course);
            return lesson;
        }).collect(Collectors.toList());
        course.setLessons(lessons);
       
        return course;
    }
    
    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-End" -> Category.FRONT_END;
            case "Back-End" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }

}
