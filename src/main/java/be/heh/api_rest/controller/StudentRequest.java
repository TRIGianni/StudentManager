package be.heh.api_rest.controller;

import java.time.LocalDate;

public record StudentRequest(String firstName, String lastName, String email,
                             LocalDate dateOfBirth) {
}
