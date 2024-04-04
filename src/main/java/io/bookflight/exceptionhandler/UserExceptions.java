package io.bookflight.exceptionhandler;

import lombok.Data;


public class UserExceptions extends RuntimeException{
    private String errorMessage;
    private Integer errorStatusCode;

    public UserExceptions(){

    }

    public UserExceptions(String errorMessage, Integer errorStatusCode){
        this.errorMessage=errorMessage;
        this.errorStatusCode=errorStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorStatusCode() {
        return errorStatusCode;
    }

    public void setErrorStatusCode(Integer errorStatusCode) {
        this.errorStatusCode = errorStatusCode;
    }

    @Override
    public String toString() {
        return "UserExceptions{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorStatusCode=" + errorStatusCode +
                '}';
    }
}
