package mk.ukim.finki.dashw.model.exceptions;

public class ProblemSolvingError extends RuntimeException {
    public ProblemSolvingError() {
        super("For some places we cannot update the search count");
    }
}
