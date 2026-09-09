package be.heh.api_rest.service;

import be.heh.api_rest.exceptions.StudentTooYoungException;
import be.heh.api_rest.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private static final int MINIMUM_AGE = 16;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {

        // Normalisation des données
        Student normalizedStudent = student
                .withNormalizedEmail()
                .withCapitalizedNames();
        // Âge minimum requis pour l'inscription
        int effectiveAge = normalizedStudent.computeAgeFromDateOfBirth();

        if (effectiveAge < MINIMUM_AGE) {
            throw new StudentTooYoungException(MINIMUM_AGE);
        }
        normalizedStudent = studentRepository.save(normalizedStudent);

        return normalizedStudent;
    }
}
