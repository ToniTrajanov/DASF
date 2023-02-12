package mk.ukim.finki.dashw.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException {
    public InvalidUserCredentialException() {
        super("Invalid user credentials");
    }
}
