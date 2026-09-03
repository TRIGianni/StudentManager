package be.heh.api_rest.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class StudentController {

    @PostMapping("students")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentRequest sr,
                                                         UriComponentsBuilder uriBuilder) {

        Long id = 100L;
        StudentResponse studentResponse = new StudentResponse(id,sr.firstName(),sr.lastName(),
                sr.email(),sr.dateOfBirth());

        URI location = uriBuilder
                .path("/api/students/{id}")
                .buildAndExpand(studentResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(studentResponse);
    }
    @GetMapping("students/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {

        StudentResponse studentResponse = new StudentResponse(id,"Alice","Bob",
                "alice@gmail.com", LocalDate.of(1980, 1, 1));

        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("students")
    public ResponseEntity<StudentResponse> searchStudents(
            @RequestParam(required = false) String lastName) {

        StudentResponse studentResponse = new StudentResponse(100L,"Alice",lastName,
                "alice@gmail.com", LocalDate.of(1980, 1,1));

        return ResponseEntity.ok(studentResponse);
    }

}
