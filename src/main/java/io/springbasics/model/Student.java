package io.springbasics.model;


import java.time.LocalDate;
import java.time.Period;

import javax.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Student {
	
	@Id
	@SequenceGenerator(
			name ="student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
			)
	private Long id;
	private String name;
	public Student(String name, String email, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
//		this.age = age;
	}
	private String email;
	private LocalDate dob;
	
	@Transient
	private Integer age; //column will not be added to database
	
	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

}
