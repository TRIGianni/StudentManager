package be.heh.api_rest.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("L'email '" + email + "' est déjà utilisé par un autre étudiant");
    }
}
