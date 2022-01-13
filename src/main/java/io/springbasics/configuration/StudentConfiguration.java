package io.springbasics.configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.springbasics.model.Student;
import io.springbasics.repository.StudentRepository;

@Configuration
public class StudentConfiguration {
	
	@Bean
	CommandLineRunner lineRunner (StudentRepository studentRepository) {
		
		return args ->{
				Student student1 = new Student(
//				1L,
				"Mariam",
				"Miriam@me.com",
				LocalDate.of(2000, Month.JULY, 20)
				
				);
				
				Student student2 = new Student(
//						1L,
						"Eric",
						"eric@me.com",
						LocalDate.of(2005, Month.AUGUST, 20)
						);	
				
				studentRepository.saveAll(
						Arrays.asList(
								student1, student2
								)			
						
						);
				
				
			
		};
			
		}	
	}
