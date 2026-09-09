package be.heh.api_rest.repository;

import be.heh.api_rest.service.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcStudentRepository implements StudentRepository {

private final JdbcClient jdbcClient;

    public JdbcStudentRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public Student save(Student student) {
        Long generatedId = jdbcClient.sql("INSERT INTO student (first_name,last_name,email,date_of_birth) " +
                        "VALUES (?,?,?,?) RETURNING id")
                .params(student.getFirstName(), student.getLastName(), student.getEmail(), student.getDateOfBirth())
                .query(Long.class)
                .single();

        return student.withId(generatedId);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findByLastNameContaining(String lastName) {
        return List.of();
    }
}
