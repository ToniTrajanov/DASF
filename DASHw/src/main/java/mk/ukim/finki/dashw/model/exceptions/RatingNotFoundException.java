package mk.ukim.finki.dashw.model.exceptions;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException() {
        super("Rating not found");
    }
}
