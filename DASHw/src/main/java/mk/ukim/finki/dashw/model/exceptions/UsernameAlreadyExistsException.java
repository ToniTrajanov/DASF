package mk.ukim.finki.dashw.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Username: " +username + "does not exist");
    }
}
