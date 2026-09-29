package be.heh.api_rest.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex,
                                                HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Erreur de validation des données");
        problem.setTitle("Requête invalide");
        problem.setType(URI.create("http://localhost:8080/api/errors/validation-error"));
        problem.setInstance(URI.create(request.getRequestURI()));

        List<Map<String, String>> fieldErrors = ex.getBindingResult().
                getFieldErrors()
                .stream()
                .map(fe -> Map.of("field", fe.getField(), "message",
                        fe.getDefaultMessage()))
                .collect(Collectors.toList());
        problem.setProperty("fieldErrors", fieldErrors);
        return problem;
    }

    // 422 - Âge minimum non atteint (règle métier)
    @ExceptionHandler(StudentTooYoungException.class)
    public ProblemDetail handleStudentTooYoung(
            StudentTooYoungException ex,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());

        problem.setTitle("Âge minimum non atteint");
        problem.setType(URI.create("http://localhost:8080/errors/student-too-young"));
        problem.setInstance(URI.create(request.getRequestURI()));

        return problem;
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ProblemDetail handleStudentNotFound(
            StudentNotFoundException ex,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());

        problem.setTitle("Étudiant introuvable");
        problem.setType(URI.create("http://localhost:8080/api/errors/student-not-found"));
        problem.setInstance(URI.create(request.getRequestURI()));

        return problem;
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ProblemDetail handleEmailAlreadyUsed(
            EmailAlreadyUsedException ex,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage());

        problem.setTitle("Email déjà utilisé");
        problem.setType(URI.create("http://localhost:8080/api/errors/email-already-used"));
        problem.setInstance(URI.create(request.getRequestURI()));

        return problem;
    }
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedError(
            Exception ex,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur inattendue est survenue");

        problem.setTitle("Erreur interne");
        problem.setInstance(URI.create(request.getRequestURI()));

        return problem;
    }
}