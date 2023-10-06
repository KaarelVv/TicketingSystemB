package ee.sda.ticketingsystem.exception;




import javax.naming.AuthenticationException;


public class InvalidPasswordException extends AuthenticationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
