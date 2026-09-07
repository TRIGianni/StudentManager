package be.heh.api_rest.controller;

import be.heh.api_rest.service.Student;
import be.heh.api_rest.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/")

@Tag(name = "Students", description = "Gestion des étudiants")
public class StudentController {

    private StudentService studentService;
    private final StudentWebMapper mapper;

    public StudentController(StudentService studentService, StudentWebMapper mapper) {
        this.studentService = studentService;
        this.mapper = mapper;
    }

    @Operation(summary = "Créer un étudiant", description = "Enregistre un nouvel étudiant et retourne son URI de localisation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("students")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentRequest sr,
                                                         UriComponentsBuilder uriBuilder) {

        Student student = mapper.toDomain(sr);
        Student createdStudent = studentService.createStudent(student);
        StudentResponse studentResponse = mapper.toResponse(createdStudent);
        URI location = uriBuilder
                .path("/api/students/{id}")
                .buildAndExpand(studentResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(studentResponse);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<StudentResponse> getStudent(
            @Parameter(description = "Identifiant de l'étudiant", example = "1")
            @PathVariable Long id) {

        StudentResponse studentResponse = new StudentResponse(id, "Alice", "Bob",
                "alice@gmail.com", LocalDate.of(1980, 1, 1));

        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("students")
    public ResponseEntity<StudentResponse> searchStudents(
            @Parameter(description = "Nom de famille (ou partie du nom) à rechercher", example = "Dupont")
            @RequestParam(required = false) String lastName) {

        StudentResponse studentResponse = new StudentResponse(100L, "Alice", lastName,
                "alice@gmail.com", LocalDate.of(1980, 1, 1));

        return ResponseEntity.ok(studentResponse);
    }

}
