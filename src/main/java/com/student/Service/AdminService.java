package com.student.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.Exception.StudentException;
import com.student.Model.Admin;
import com.student.Model.Course;
import com.student.Model.Student;
import com.student.Repository.AdminRepo;
import com.student.Repository.CourseRepo;
import com.student.Repository.StudentRepo;

@Service
public class AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CourseRepo courseRepo;

	public Admin addAdmin(Admin admin) throws StudentException {
		Optional<Admin> admins = adminRepo.findByEmail(admin.getEmail());
		if (!admins.isPresent()) {
			throw new StudentException("Duplicate Admin find with this email: " + admin.getEmail());
		}
		return adminRepo.save(admins.get());
	}

	public List<Student> getAllStudent() throws StudentException {
		List<Student> student = studentRepo.findAll();
		if (student.isEmpty()) {
			throw new StudentException("Student is not added yet");
		}
		return student;
	}

	public Student getStudentById(Long studentId) {
		Optional<Student> st = studentRepo.findById(studentId);
		if (st.isPresent()) {
			return st.get();
		} else {
			throw new StudentException("Student not found with ID:" + studentId);
		}
	}

	public Course addCourse(Course course) {
		Optional<Course> courses = courseRepo.findByCourseName(course.getCourseName());
		if (courses.isPresent()) {
			throw new StudentException("Courses is already available");
		}
		return courseRepo.save(courses.get());
	}

	public String deleteCourse(String studentCode) {
		Optional<Student> stu = studentRepo.findByStudentCode(studentCode);
		if (stu.isPresent()) {
			studentRepo.deleteById(stu.get().getStudent_id());
			return "Student Deleted Succesfully";
		} else {
			throw new StudentException("Student not found with this Student ID: " + studentCode);
		}
	}
}
