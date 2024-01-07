package com.student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.Model.Course;
import com.student.Model.Student;
import com.student.Service.AdminService;
import com.student.Service.StudentService;

@RestController
public class AdminController {

	@Autowired(required = false)
	private StudentService studentService;

	@Autowired
	private AdminService adminService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> stu = adminService.getAllStudent();
		return new ResponseEntity<List<Student>>(stu, HttpStatus.OK);
	}

	@PostMapping("/course")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		Course courses = adminService.addCourse(course);
		return new ResponseEntity<Course>(courses, HttpStatus.CREATED);
	}

//	@DeleteMapping("/students/{student_id}")
//	public ResponseEntity<String>deleteStudent(@PathVariable Long studentId){
//		Student stu=studentService
//	}

}
