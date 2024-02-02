package com.student.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.DTO.AddressDTO;
import com.student.DTO.CourseDTO;
import com.student.DTO.StudentDTO;
import com.student.Exception.StudentManagementException;
import com.student.Model.Address;
import com.student.Model.Admin;
import com.student.Model.Course;
import com.student.Model.Role;
import com.student.Model.Student;
import com.student.Repository.AddressRepo;
import com.student.Repository.AdminRepo;
import com.student.Repository.CourseRepo;
import com.student.Repository.StudentRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ModelMapper modelMapper;

	public Admin registerAdmin(Admin admin) throws StudentManagementException {
		Optional<Admin> admins = adminRepo.findByEmail(admin.getEmail());
		if (admins.isPresent()) {
			throw new StudentManagementException("Duplicate Admin find with this email: " + admin.getEmail());
		}
		admin.setRole(Role.Admin);
		return adminRepo.save(admin);
	}

	public Student addStudent(StudentDTO studentDTO) throws StudentManagementException {
		Student student = modelMapper.map(studentDTO, Student.class);
		Optional<Student> stu = studentRepo.findByEmail(student.getEmail());

		stu.ifPresent(exitingStudent -> {
			throw new StudentManagementException("Student already exists with this email: " + student.getEmail());
		});

		student.setRole(Role.Student);
		return studentRepo.save(student);

	}

	public Address addAddressToStudent(AddressDTO addressDTO, String studentCode) throws StudentManagementException {
		try {
			Optional<Student> studentOptional = studentRepo.findByStudentCode(studentCode);

			if (studentOptional.isPresent()) {
				Address address = modelMapper.map(addressDTO, Address.class);
				Student student = studentOptional.get();

				student.getAddress().add(address);
				address.setStudent(student);

				studentRepo.save(student);
				addressRepo.save(address);

				return address;
			} else {
				throw new StudentManagementException("Student does not exist with this studentCode: " + studentCode);
			}

		} catch (Exception e) {
			throw new StudentManagementException("Failed to add address to student. Something went wrong.", e);
		}
	}

	public Course addCourse(CourseDTO courseDTO) throws StudentManagementException {

		try {
			Course course = modelMapper.map(courseDTO, Course.class);
			Optional<Course> existingCourse = courseRepo.findBycourseName(course.getCourseName());

			if (existingCourse.isPresent()) {
				throw new StudentManagementException(
						"Course with Name " + course.getCourseName() + " is already available");
			} else {
				return courseRepo.save(course);
			}

		} catch (Exception e) {
			throw new StudentManagementException("Failed to add the course. Something went wrong." + e);
		}
	}

	public void assignCourseToStudent(Long courseId, String studentCode) throws StudentManagementException {

		try {

			Student student = studentRepo.findByStudentCode(studentCode)
					.orElseThrow(() -> new StudentManagementException("Student not found with id: " + studentCode));

			Course course = courseRepo.findById(courseId)
					.orElseThrow(() -> new StudentManagementException("Course not found with id: " + courseId));

			Set<Course> studentCourse = student.getCourses();
			Set<Student> coursePurchasedByStudent = course.getStudents();

			if (!studentCourse.contains(course)) {
				studentCourse.add(course);
				coursePurchasedByStudent.add(student);
				courseRepo.save(course);
				studentRepo.save(student);

			} else {
				throw new StudentManagementException(
						"This course has already been purchased : " + course.getCourseName());
			}

		} catch (Exception e) {
			throw new StudentManagementException("Error while assigning courses to student");
		}
	}

	public List<Student> getAllStudent() throws StudentManagementException {
		List<Student> student = studentRepo.findAll();
		if (student.isEmpty()) {
			throw new StudentManagementException("Student is not added yet");
		}
		return student;
	}

	public Student getStudentById(String email) throws StudentManagementException {
		Optional<Student> st = studentRepo.findByEmail(email);
		if (st.isPresent()) {
			return st.get();
		} else {
			throw new StudentManagementException("Student not found with email:" + email);
		}
	}

	public String deleteCourse(Long courseId) throws StudentManagementException {
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new StudentManagementException("Course not found with this courseId : " + courseId));

		Set<Student> student = course.getStudents();
		if (student.isEmpty()) {
			courseRepo.deleteById(courseId);
			return "Course Deleted Succesfully";
		} else {
			throw new StudentManagementException("Student are Allready Enrolled with this CourseId : " + courseId);
		}
	}

}
