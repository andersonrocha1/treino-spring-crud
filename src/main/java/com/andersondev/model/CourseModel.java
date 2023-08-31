package com.andersondev.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.andersondev.enums.Category;
import com.andersondev.enums.Status;
import com.andersondev.enums.converters.CategoryConverter;
import com.andersondev.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
@SQLDelete(sql = "UPDATE tb_courses SET status = 'Inativo' WHERE id = ? ")
@Where(clause = "status = 'Ativo'")
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
	@Column(length = 12, nullable = false)
	@Convert(converter = CategoryConverter.class)
	private Category category;
	
	@NotNull
	@Column(length = 10, nullable = false)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ACTIVE;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
	private List<Lesson> lessons = new ArrayList<>();
	
	
	public CourseModel() {
		
	}


	public CourseModel(Long id, @NotBlank @NotNull @Length(min = 5, max = 120) String name, @NotNull Category category,
			@NotNull Status status, List<Lesson> lessons) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.status = status;
		this.lessons = lessons;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
