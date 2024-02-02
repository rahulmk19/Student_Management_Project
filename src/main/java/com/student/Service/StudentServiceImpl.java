package com.student.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.Exception.StudentManagementException;
import com.student.Model.Address;
import com.student.Model.Course;
import com.student.Model.Student;
import com.student.Repository.AddressRepo;
import com.student.Repository.CourseRepo;
import com.student.Repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private AddressRepo addressRepo;

	public Student updateStudentDetails(Long studentId, Student st) throws StudentManagementException {
		try {
			Student student = studentRepo.findById(studentId).get();
			student.setEmail(st.getEmail());
			student.setMobileNumber(st.getMobileNumber());
			student.setAddress(st.getAddress());
			student.setDateOfBirth(st.getDateOfBirth());
			student.setStudentName(st.getStudentName());
			return studentRepo.save(student);
		} catch (Exception ex) {
			throw new StudentManagementException("Student not found with ID:" + studentId);
		}
	}

	public Set<Course> getAllCourse(Long studentCode) throws StudentManagementException {
		Set<Course> allCourse = studentRepo.findById(studentCode).get().getCourses();
		if (allCourse.isEmpty()) {
			throw new StudentManagementException("Course is not purchased yet");
		}
		return allCourse;
	}

	public String assignCourseStudent(Long studentId, Long courseId) throws StudentManagementException {
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
				throw new StudentManagementException("Course allready has purchased");
			}

		} catch (Exception ex) {
			throw new StudentManagementException(ex.getMessage());
		}

	}

	public String leaveCourse(Long studentId, Long courseId) throws StudentManagementException {
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
			throw new StudentManagementException("Error occurs while leave from the course");
		}
	}

	public Address addAddress(Address address, Long studentId) throws StudentManagementException {
		try {
			Student student = studentRepo.findById(studentId)
					.orElseThrow(() -> new StudentManagementException("Student not found"));
			address.setStudent(student);
			Address addedAddress = addressRepo.save(address);
			student.getAddress().add(addedAddress);
			studentRepo.save(student);
			return addedAddress;
		} catch (Exception ex) {
			throw new StudentManagementException("Not added address with student", ex);
		}
	}

}
