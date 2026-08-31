package be.heh.api_rest.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class StudentController {

    @PostMapping("students")
    public void createStudent(@RequestBody @Valid StudentRequest sr) {
        System.out.println("Fisrtname :" + sr.firstName());
        System.out.println("LastName :" + sr.lastName());
        System.out.println("email :" + sr.email());
        System.out.println("dateOfBirth :" + sr.dateOfBirth());
    }
}
