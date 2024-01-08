# Student Management System
The Student Management System is a comprehensive solution designed to empower educational institutions in managing student-related operations seamlessly. Leveraging the robust capabilities of Spring Boot for REST API development and JPA with Hibernate for data persistence, this system provides a scalable and efficient platform for both administrators and students.

## Technologies Used

**Backend:**
- Spring Boot
- Java
- JPA
- Hibernate
- MySQL

## API Endpoints
### Swagger 
- ** http://localhost:8080/swagger-ui.html

### When using a local database
##  Student Operation
- **POST:** http://localhost:8080/students
- **GET:** http://localhost:8080/students
- **PUT:** http://localhost:8080/students/{studentId}
- 
- **GET:** http://localhost:8080/course 
- **POST:** http://localhost:8080/students/{studentId}/course/{courseId} [Purchase Course]
- **DELETE:** http://localhost:8080/students/{studentId}/course/{courseId} [Student Leave the Course]

##  Admin Operation
- **GET:** http://localhost:8080/students  [ Get All the Student ] 
- **POST:** http://localhost:8080/course [ Purchase Course ]

#Message
Thank you so much for taking the time to visit my profile and delve into the project. Your interest is truly appreciated! If you have any questions or feedback, feel free to reach out. Happy exploring!
