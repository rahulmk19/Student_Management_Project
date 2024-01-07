package com.student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Model.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
	public Optional<Course> findByCourseName(String courseName);
}
