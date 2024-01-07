package com.student.Controller;

import java.util.List;

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
import com.student.Service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/students")
	public ResponseEntity<Student> addUser(@RequestBody Student stu) {
		Student student = studentService.addStudent(stu);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	@PutMapping("/students/{studentId}")
	public ResponseEntity<Student> updateStudentDetails(@RequestBody Student student, @PathVariable Long studentId) {
		Student stu = studentService.updateStudentDetails(studentId, student);
		return new ResponseEntity<Student>(stu, HttpStatus.ACCEPTED);
	}

	@GetMapping("/course")
	public ResponseEntity<List<Course>> getAllCourse() {
		List<Course> allCourse = studentService.getAllCourse();
		return new ResponseEntity<List<Course>>(allCourse, HttpStatus.ACCEPTED);
	}

	@PostMapping("students/{studentId}/course/{courseId}")
	public ResponseEntity<String> assignCourseStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
		try {
			String msg = studentService.assignCourseStudent(studentId, courseId);
			return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Error :" + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
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

	@PostMapping("/address")
	public ResponseEntity<Address> addAdress(@RequestBody Address address) {
		Address add = studentService.addAddress(address);
		return new ResponseEntity<Address>(add, HttpStatus.CREATED);
	}

//	pubilc ResponseEntity<>

}
