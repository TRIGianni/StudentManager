package be.heh.api_rest.controller;

import be.heh.api_rest.service.Student;
import be.heh.api_rest.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
@AutoConfigureRestTestClient
class StudentControllerTest {
    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private StudentWebMapper mapper;

    @Test
    void createStudent_shouldReturn201AndLocation() {
        StudentRequest request = new StudentRequest("Jean", "Dupont", "jean.dupont@mail.com", LocalDate.of(1990, 1, 1));
        Student domainToSave = new Student(null, "Jean", "Dupont", "jean.dupont@mail.com", LocalDate.of(1990, 1, 1));
        Student savedStudent = new Student(1L, "Jean", "Dupont", "jean.dupont@mail.com", LocalDate.of(1990, 1, 1));
        StudentResponse response = new StudentResponse(1L, "Jean", "Dupont", "jean.dupont@mail.com", LocalDate.of(1990, 1, 1));

        when(mapper.toDomain(request)).thenReturn(domainToSave);
        when(studentService.createStudent(domainToSave)).thenReturn(savedStudent);
        when(mapper.toResponse(savedStudent)).thenReturn(response);

        restTestClient.post()
                .uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost/api/students/1")
                .expectBody(StudentResponse.class)
                .isEqualTo(response);

        verify(studentService).createStudent(domainToSave);
    }

        @Test
        void getStudent() {
        }

        @Test
        void searchStudents() {

        }
    }