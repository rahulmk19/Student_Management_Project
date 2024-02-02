package com.student.Service;

import java.util.List;

import com.student.DTO.AddressDTO;
import com.student.DTO.CourseDTO;
import com.student.DTO.StudentDTO;
import com.student.Exception.StudentManagementException;
import com.student.Model.Address;
import com.student.Model.Admin;
import com.student.Model.Course;
import com.student.Model.Student;

public interface AdminService {

	public Admin registerAdmin(Admin admin) throws StudentManagementException;

	public Student addStudent(StudentDTO studentDTO) throws StudentManagementException;

	public Address addAddressToStudent(AddressDTO addressDTO, String studentCode) throws StudentManagementException;

	public Course addCourse(CourseDTO courseDTO) throws StudentManagementException;

	public void assignCourseToStudent(Long courseId, String studentCode) throws StudentManagementException;

	public List<Student> getAllStudent() throws StudentManagementException;

	public Student getStudentById(String email) throws StudentManagementException;

	public String deleteCourse(Long courseId) throws StudentManagementException;
}
