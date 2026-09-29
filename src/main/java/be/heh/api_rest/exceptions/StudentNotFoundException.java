package be.heh.api_rest.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
      super("Aucun étudiant trouvé avec l'identifiant "+id);
    }
}
