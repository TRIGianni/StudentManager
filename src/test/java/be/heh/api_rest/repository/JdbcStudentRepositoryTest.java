package be.heh.api_rest.repository;

import be.heh.api_rest.service.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;


@JdbcTest
@Testcontainers
@Import(JdbcStudentRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcStudentRepositoryTest {

    @Autowired
    private JdbcStudentRepository jdbcStudentRepository;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    @Sql("/schema.sql")
    void save_shouldPersistStudentAndAssignGeneratedId() {
        Student studentToSave = new Student("Jean", "Dupont", "jean.dupont@mail.com", LocalDate.of(1990, 1, 1));
        Student studentSaved = jdbcStudentRepository.save(studentToSave);

        assertThat(studentSaved.getId()).isNotNull();
        assertThat(studentSaved.getFirstName()).isEqualTo("Jean");
        assertThat(studentSaved.getLastName()).isEqualTo("Dupont");
        assertThat(studentSaved.getEmail()).isEqualTo("jean.dupont@mail.com");
        assertThat(studentSaved.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(studentSaved.getId()).isEqualTo(1);
    }
}