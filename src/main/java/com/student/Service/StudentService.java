package com.student.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.Exception.StudentException;
import com.student.Model.Address;
import com.student.Model.Course;
import com.student.Model.Student;
import com.student.Repository.AddressRepo;
import com.student.Repository.CourseRepo;
import com.student.Repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private AddressRepo addressRepo;

	public Student addStudent(Student student) {
		Optional<Student> stu = studentRepo.findByEmail(student.getEmail());
		if (stu.isPresent()) {
			throw new StudentException("Student allready available with this studentID: " + student.getEmail());
		} else {
			String studentCode = GenerateStudentCode.generateRandomString(student.getStudent_name());
			System.out.println(studentCode);
			student.setStudentCode(studentCode);
			student.setRole("Student");
			return studentRepo.save(student);
		}
	}

	public Student updateStudentDetails(Long studentId, Student st) {
		try {
			Student student = studentRepo.findById(studentId).get();
			student.setEmail(st.getEmail());
			student.setMobileNumber(st.getMobileNumber());
			student.setAddress(st.getAddress());
			student.setDateOfBirth(st.getDateOfBirth());
			student.setStudent_name(st.getStudent_name());
			return studentRepo.save(student);
		} catch (Exception ex) {
			throw new StudentException("Student not found with ID:" + studentId);
		}
	}

	public List<Course> getAllCourse() throws StudentException {
		List<Course> course = courseRepo.findAll();
		if (course.isEmpty()) {
			throw new StudentException("Course is not available");
		}
		return course;
	}

	public String assignCourseStudent(Long studentId, Long courseId) throws StudentException {
		try {
			Student stu = studentRepo.findById(studentId).get();
			Course course = courseRepo.findById(courseId).get();

			Set<Course> allCourse = stu.getCourses();
			if (!allCourse.contains(course)) {
				stu.getCourses().add(course);
				course.getStudents().add(stu);
				studentRepo.save(stu);
				courseRepo.save(course);
				return "Course Purchase succesfully " + course.getCourseName();
			} else {
				throw new StudentException("Course allready has purchased");
			}

		} catch (Exception ex) {
			throw new StudentException(ex.getMessage());
		}

	}

	public String leaveCourse(Long studentId, Long courseId) throws StudentException {
		try {
			Student student = studentRepo.findById(studentId).get();
			Course course = courseRepo.findById(courseId).get();

			Set<Course> allCourse = student.getCourses();
			if (allCourse.contains(course)) {
				allCourse.remove(course);
				studentRepo.save(student);
				return "Leave the course succesfully " + courseId;
			} else {
				return "Error occurs while leave from the course";
			}
		} catch (Exception ex) {
			throw new StudentException("Error occurs while leave from the course");
		}
	}

	public Address addAddress(Address address) throws StudentException {
		return addressRepo.save(address);
	}

}
