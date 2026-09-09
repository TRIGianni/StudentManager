package be.heh.api_rest.exceptions;

public class StudentTooYoungException extends RuntimeException{
    public StudentTooYoungException() {
    }
    public StudentTooYoungException(int minimumAge) {
        super("L'étudiant doit avoir au moins " + minimumAge + " ans pour s'inscrire");
    }
}
