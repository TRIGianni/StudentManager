package be.heh.api_rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

class StudentControllerTest {
    private RestTestClient client;

    @BeforeEach
    void setUp() {
        client = RestTestClient.bindToController(new StudentController()).build();
    }

    @Test
    void createStudent() {
        String requestBody = """
                {
                  "firstName": "Jean",
                  "lastName": "Dupont",
                  "email": "jean.dupont@example.com",
                  "dateOfBirth": "2005-03-15"
                }
                """;

        client.post()
                .uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader()
                .valueMatches("Location", ".*/api/students/100$")
                .expectBody()
                .jsonPath("$.id").isEqualTo(100)
                .jsonPath("$.firstName").isEqualTo("Jean")
                .jsonPath("$.lastName").isEqualTo("Dupont")
                .jsonPath("$.email").isEqualTo("jean.dupont@example.com");
    }

    @Test
    void getStudent() {
        client.get()
                .uri("/api/students/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.firstName").isEqualTo("Alice")
                .jsonPath("$.lastName").isEqualTo("Bob")
                .jsonPath("$.email").isEqualTo("alice@gmail.com");
    }

    @Test
    void searchStudents() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/students").
                        queryParam("lastName", "Dupont").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.lastName").isEqualTo("Dupont")
                .jsonPath("$.email").isEqualTo("alice@gmail.com");
    }
}
