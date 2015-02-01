package ro.cti.ssa.twittboost.exception;

/**
 * @author adrian.zamfirescu
 * @since 23/01/2015
 */
public class AppUserCreationException extends Exception{

    private String message;

    public AppUserCreationException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
