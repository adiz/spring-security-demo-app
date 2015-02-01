package ro.cti.ssa.twittboost.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 23/01/2015
 */
public class ControllerException extends Exception{

    private String message;

    public ControllerException(String message){
        super(message);
        this.message = message;
    }

    public ControllerException(List<FieldError> errors){

        StringBuilder messageBuilder = new StringBuilder();
        for (FieldError error : errors)
            messageBuilder.append(error.getDefaultMessage()).append("<br/>");

        message = messageBuilder.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
