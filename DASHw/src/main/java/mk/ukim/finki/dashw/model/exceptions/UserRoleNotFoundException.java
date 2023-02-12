package mk.ukim.finki.dashw.model.exceptions;

public class UserRoleNotFoundException extends RuntimeException {
    public UserRoleNotFoundException() {
        super("User role is not found");
    }
}
