package be.heh.api_rest.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentResponse(Long id,String firstName, String lastName, String email, LocalDate dateOfBirth) {
}