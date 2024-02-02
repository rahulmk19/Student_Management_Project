package com.student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.DTO.AddressDTO;
import com.student.DTO.CourseDTO;
import com.student.DTO.StudentDTO;
import com.student.Exception.StudentManagementException;
import com.student.Model.Address;
import com.student.Model.Admin;
import com.student.Model.Course;
import com.student.Model.Student;
import com.student.Service.AdminServiceImpl;
import com.student.Service.StudentServiceImpl;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired(required = false)
	private StudentServiceImpl studentService;

	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping("/admin")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
		Admin register = adminService.registerAdmin(admin);
		return new ResponseEntity<Admin>(register, HttpStatus.CREATED);
	}

	@PostMapping("/students")
	public ResponseEntity<Student> addStudent(@RequestBody StudentDTO stu) {
		Student student = adminService.addStudent(stu);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	@PostMapping("/address/{studentCode}")
	public ResponseEntity<Address> addAdressToStudent(@RequestBody AddressDTO address,
			@PathVariable String studentCode) {
		Address addedAddress = adminService.addAddressToStudent(address, studentCode);
		return new ResponseEntity<Address>(addedAddress, HttpStatus.CREATED);
	}

	@PostMapping("/course")
	public ResponseEntity<Course> addCourse(@RequestBody CourseDTO courseDTO) {
		try {
			Course course = adminService.addCourse(courseDTO);
			return new ResponseEntity<>(course, HttpStatus.CREATED);
		} catch (StudentManagementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

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

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> stu = adminService.getAllStudent();
		return new ResponseEntity<List<Student>>(stu, HttpStatus.OK);
	}

//	@DeleteMapping("/students/{student_id}")
//	public ResponseEntity<String>deleteStudent(@PathVariable Long studentId){
//		Student stu=studentService
//	}

}
