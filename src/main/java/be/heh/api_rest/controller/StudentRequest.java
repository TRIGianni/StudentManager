package be.heh.api_rest.controller;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentRequest( @NotBlank(message = "Le prénom est obligatoire")
                              @Size(max = 50) String firstName,
                              @NotBlank(message = "Le nom est obligatoire")
                              @Size(max = 50) String lastName,
                              @NotBlank(message = "L'email est obligatoire")
                              @Email(message = "L'email doit être valide") String email,
                              @Past @NotNull(message = "La date de naissance doit être dans le passé")LocalDate dateOfBirth) {
}
