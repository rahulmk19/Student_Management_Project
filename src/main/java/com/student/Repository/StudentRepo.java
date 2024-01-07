package com.student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);

	Optional<Student> findByStudentCode(String studentCode);

}
