package be.heh.api_rest.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/")
public class StudentController {

    @PostMapping("students")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentRequest sr,
                                                         UriComponentsBuilder uriBuilder) {

        int id = 100;
        StudentResponse studentResponse = new StudentResponse(id,sr.firstName(),sr.lastName(),
                sr.email(),sr.dateOfBirth());

        URI location = uriBuilder
                .path("/api/students/{id}")
                .buildAndExpand(studentResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(studentResponse);
    }
}
