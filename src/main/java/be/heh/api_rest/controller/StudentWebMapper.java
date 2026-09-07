package be.heh.api_rest.controller;

import be.heh.api_rest.service.Student;
import org.springframework.stereotype.Component;
@Component
public class StudentWebMapper {
    // Sens entrant : requête web -> modèle de domaine
    public Student toDomain(StudentRequest request) {
        return new Student(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.dateOfBirth()
        );
    }
    // Sens sortant : modèle de domaine -> réponse web
    public StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth()
        );
    }
}
