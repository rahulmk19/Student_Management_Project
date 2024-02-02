package com.student.Service;

import java.util.Set;

import com.student.Exception.StudentManagementException;
import com.student.Model.Course;
import com.student.Model.Student;

public interface StudentService {

	public Student updateStudentDetails(Long studentId, Student st) throws StudentManagementException;

	public Set<Course> getAllCourse(Long studentCode) throws StudentManagementException;

	public String assignCourseStudent(Long studentId, Long courseId) throws StudentManagementException;

	public String leaveCourse(Long studentId, Long courseId) throws StudentManagementException;
}
