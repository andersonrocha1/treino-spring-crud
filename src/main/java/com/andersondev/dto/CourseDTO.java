package com.andersondev.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
		
		@JsonProperty("_id") Long id, 
		@NotBlank @NotNull @Length(min = 5, max = 120) String name, 
		@NotNull @Length(max = 12) @Pattern(regexp = "Front-End|Back-End") String category) {

}
