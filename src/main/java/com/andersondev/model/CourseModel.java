package com.andersondev.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="tb_courses")
public class CourseModel implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("_id")
	private Long id;
	
	@NotBlank
	@NotNull
	@Length(min = 5, max = 120)
	@Column(length = 120, nullable = false)
	private String name;
	
	@NotNull
	@Length(max = 12)
	@Pattern(regexp = "Front-End|Back-End")
	@Column(length = 12, nullable = false)
	private String category;
	
	
}
