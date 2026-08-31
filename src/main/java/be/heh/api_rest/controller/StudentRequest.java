package be.heh.api_rest.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentRequest( @NotBlank(message = "Le prénom est obligatoire")
                              @Size(max = 50) String firstName,
                              @NotBlank(message = "Le nom est obligatoire")
                              @Size(max = 50) String lastName,
                              @NotBlank(message = "L'email est obligatoire")
                              @Email(message = "L'email doit être valide") String email,
                              @Past(message = "La date de naissance doit être dans le passé")LocalDate dateOfBirth) {
}
