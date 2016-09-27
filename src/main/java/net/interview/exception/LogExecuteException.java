package net.interview.exception;

/**
 * Created by xiu on 9/26/16.
 */
public class LogExecuteException extends Exception {
    private String errorCode="Unknown_Exception";

    public LogExecuteException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }

    public String getErrorCode(){
        return this.errorCode;
    }



}
