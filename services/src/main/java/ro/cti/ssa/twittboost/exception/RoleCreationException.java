package ro.cti.ssa.twittboost.exception;

/**
 * @author adrian.zamfirescu
 * @since 01/02/2015
 */
public class RoleCreationException extends Exception{

    private String message;

    public RoleCreationException(String message){
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
