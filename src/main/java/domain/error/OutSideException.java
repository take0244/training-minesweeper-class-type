package domain.error;

public class OutSideException extends RuntimeException {
    public OutSideException(final int x, final int y) {
        super("Outside domain.Board x:" + x + " y:" + y);
    }
}
