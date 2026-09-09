package be.heh.api_rest.repository;

import be.heh.api_rest.service.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    boolean existsByEmail(String email);

    Student save(Student student);

    Optional<Student> findById(Long id);

    List<Student> findByLastNameContaining(String lastName);
}
