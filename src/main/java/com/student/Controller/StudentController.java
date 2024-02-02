package com.student.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.Model.Address;
import com.student.Model.Course;
import com.student.Model.Student;
import com.student.Service.StudentServiceImpl;

import jakarta.validation.Valid;

@RestController
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

	@PutMapping("/students/{studentId}")
	public ResponseEntity<Student> updateStudentDetails(@RequestBody Student student, @PathVariable Long studentId) {
		Student stu = studentService.updateStudentDetails(studentId, student);
		return new ResponseEntity<Student>(stu, HttpStatus.ACCEPTED);
	}

	@GetMapping("/course")
	public ResponseEntity<Set<Course>> getAllCourse(@Valid @PathVariable Long studentCode) {
		Set<Course> allCourse = studentService.getAllCourse(studentCode);
		return new ResponseEntity<Set<Course>>(allCourse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("students/{studentId}/course/{courseId}")
	public ResponseEntity<String> leaveCourseStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
		try {
			String msg = studentService.leaveCourse(studentId, courseId);
			return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Error :" + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
