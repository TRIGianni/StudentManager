package be.heh.api_rest.service;

import java.util.List;

public interface StudentService {
   Student createStudent(Student student);
   Student getStudentById(Long id);
   List<Student> searchStudentsByLastName(String lastName);
}
