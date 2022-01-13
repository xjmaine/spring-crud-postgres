package io.springbasics.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.springbasics.model.Student;
import io.springbasics.repository.StudentRepository;

@Service
public class StudentServices {
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> allStudents() {
//		List<Student> student1 = Arrays.asList(
//				new Student(
//				1L,
//				"Mariam",
//				"Miriam@me.com",
//				LocalDate.of(2000, Month.JULY, 20),
//				22
//				)
//							
//				);
		return studentRepository.findAll();
	}

		//add new
	public void addNewStudent(Student student) {
		// validating email
		Optional<Student> newStudent =studentRepository.findStudentByEmail(student.getEmail());
		if(newStudent.isPresent()) {
			throw new IllegalStateException("Email already in use!");
		}
		studentRepository.save(student);
//		System.out.println(student);
	}

		//delete
	public void deleteStudent(Long studentId) {
		// TODO Auto-generated method stub
		boolean exists =studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);
	}
	
	//update
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId) 
				.orElseThrow(()-> new IllegalStateException(""
						+ "student with id " + studentId + " does not exist"));
		if(name != null && name.length() > 0 &&
				!Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if(email != null && email.length() > 0
				&& !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}
	}
}
