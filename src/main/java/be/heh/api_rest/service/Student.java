package be.heh.api_rest.service;

import java.time.LocalDate;
import java.time.Period;

public class Student {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDate dateOfBirth;

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth) {
        this(null, firstName, lastName, email, dateOfBirth);
    }

    public Student(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Student withId(Long id) {
        return new Student(id, firstName, lastName, email, dateOfBirth);
    }

    // Règle métier : normalisation de l'email (minuscules, sans espaces superflus)
    public Student withNormalizedEmail() {
        String normalized = email == null ? null : email.trim().toLowerCase();
        return new Student(id, firstName, lastName, normalized, dateOfBirth);
    }

    // Règle métier : normalisation des noms (première lettre en majuscule)
    public Student withCapitalizedNames() {
        return new Student(id, capitalize(firstName), capitalize(lastName), email, dateOfBirth);
    }

    private String capitalize(String value) {
        if (value == null || value.isBlank()) {
            return value;
        }
        String trimmed = value.trim();
        return Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1).toLowerCase();
    }

    // Règle métier : âge calculé à partir de la date de naissance
    public int computeAgeFromDateOfBirth() {
        if (dateOfBirth == null) {
            throw new IllegalStateException("Impossible de calculer l'âge sans date de naissance");
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}

