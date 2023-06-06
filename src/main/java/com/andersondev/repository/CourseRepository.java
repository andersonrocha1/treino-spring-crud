package com.andersondev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andersondev.model.CourseModel;


public interface CourseRepository extends JpaRepository<CourseModel, Long> {

}
