package be.heh.api_rest.service;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private static final int MINIMUM_AGE = 16;
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

        normalizedStudent = normalizedStudent.withId(100L);
        return normalizedStudent;
    }
}
