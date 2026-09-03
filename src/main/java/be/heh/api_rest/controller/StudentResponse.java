package be.heh.api_rest.controller;

import java.time.LocalDate;

public record StudentResponse(Long id,String firstName, String lastName, String email, LocalDate dateOfBirth) {
}